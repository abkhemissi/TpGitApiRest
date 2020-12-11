package fr.paris8univ.iut.csid.csidwebrepositorybase.repository.git;

import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.core.GitRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.core.Stats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.exposition.GitRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GitRepositoryRepository {

    private final GitRepositoryDao gitRepositoryDao;
    private final GithubRepositoryDao gitHubRepositoryDao;
    private final StatsDao statsDao;


    @Autowired
    public GitRepositoryRepository(GitRepositoryDao gitRepositoryDao, GithubRepositoryDao gitHubRepositoryDao, StatsDao statsDao) {
        this.gitRepositoryDao=gitRepositoryDao;
        this.gitHubRepositoryDao=gitHubRepositoryDao;
        this.statsDao = statsDao;
    }

    public List<GitRepository> getRepositories(){
        List<GitRepositoryEntity> repositoryEntities = gitRepositoryDao.findAll();


        return repositoryEntities.stream()
                .map(x -> new GitRepository(x.getName(),x.getOwner(),x.getIssues(),x.getFork(),x.getUpdate()))
                .collect(Collectors.toList());
    }

    public Optional<GitRepository> findOneRepoForPatch(String name){

        return Optional.of(gitRepositoryDao.findById(name)
                .map(x-> new GitRepository(x.getName(),x.getOwner(),x.getIssues(),x.getFork(),x.getUpdate())).get());
    }

    public Optional<GitRepository> findOneRepository(String name) throws RestClientException, URISyntaxException{
        GitRepositoryEntity actualRepository = gitRepositoryDao.findById(name).get();




        GitRepository gitRepository= new GitRepository(actualRepository.getName(),
                actualRepository.getOwner(),actualRepository.getIssues(),actualRepository.getFork(), actualRepository.getUpdate());


        GitRepositoryDTO repositoryDto = gitHubRepositoryDao.getIssuesAndForks(gitRepository.getName(), gitRepository.getOwner());

//        Date actualDate = new Date();
//        String strDate = dateFormatNormal.format(actualDate);

        String convert = actualRepository.getUpdate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(convert, formatter);

        if(this.compareMinute(dateTime,LocalDateTime.now())>5) {
            gitRepository.setIssues(repositoryDto.getIssues());
            gitRepository.setFork(repositoryDto.getForks());
            gitRepository.setUpdate(this.actual());
            patchRepository(gitRepository);
        }

        createStatsFork(repositoryDto.getForks(),repositoryDto.getName());
        createStatsIssue(repositoryDto.getIssues(),repositoryDto.getName());

        return Optional.of(gitRepository);
    }

    public void putRepository(GitRepository gitRepository) {

        Optional<GitRepositoryEntity> repository = gitRepositoryDao.findById(gitRepository.getName());

        if(repository.isEmpty()) {
            createRepository(gitRepository);
        } else {

            GitRepositoryEntity repositoryEntity = repository.get();
            repositoryEntity.setOwner(gitRepository.getOwner());
            repositoryEntity.setIssues(gitRepository.getIssues());
            repositoryEntity.setFork(gitRepository.getFork());
            repositoryEntity.setUpdate(this.actual());

            System.out.println(repositoryEntity.getUpdate());
            gitRepositoryDao.save(repositoryEntity);

        }

    }

    public void createRepository(GitRepository gitRepository){

        gitRepositoryDao.save(new GitRepositoryEntity(gitRepository.getName(),gitRepository.getOwner(),
                gitRepository.getIssues(),gitRepository.getFork(),this.actual()));

    }

    public void patchRepository(GitRepository gitRepo) {
/*        Date date = null;
        try {
            date = dateFormatEntity.parse(gitRepo.getLastUpdate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

 */

        GitRepositoryEntity repositoryPatched = new GitRepositoryEntity(gitRepo.getName(),gitRepo.getOwner(),gitRepo.getIssues(),gitRepo.getFork(),this.actual());
        gitRepositoryDao.save(repositoryPatched);
    }

    public void deleteRepository(String name) {
        gitRepositoryDao.deleteById(name);
    }

    public void createStatsFork(int value, String name){

        statsDao.save(new StatsEntity("fork",this.actual(),value,name));
    }
    public void createStatsIssue(int value, String name){

        statsDao.save(new StatsEntity("issue",this.actual(),value,name));
    }

    public String actual(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public int compareMinute(LocalDateTime from, LocalDateTime actual){
        return (int) from.until(actual, ChronoUnit.MINUTES );
    }
}
