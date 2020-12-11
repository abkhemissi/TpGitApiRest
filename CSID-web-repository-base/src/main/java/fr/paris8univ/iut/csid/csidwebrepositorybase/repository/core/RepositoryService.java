package fr.paris8univ.iut.csid.csidwebrepositorybase.repository.core;


import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.git.GitRepositoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepositoryService {

    private final GitRepositoryRepository gitRepositoryRepository;

    public RepositoryService(GitRepositoryRepository gitRepositoryRepository) {
        this.gitRepositoryRepository=gitRepositoryRepository;
    }

    public List<GitRepository> getRepositories(){
        return gitRepositoryRepository.getRepositories();
    }

    public Optional<GitRepository>  findOneRepository(String name) throws RestClientException, URISyntaxException{
        return gitRepositoryRepository.findOneRepository(name);
    }

    public void createRepository(GitRepository gitRepository){
        gitRepositoryRepository.createRepository(gitRepository);
    }

    public void putRepository(GitRepository gitRepository) {
        gitRepositoryRepository.putRepository(gitRepository);
    }

    public void patchRepository(String name, GitRepository gitRepository) {
        GitRepository gitRepositoryPatched = merge(this.gitRepositoryRepository.findOneRepoForPatch(name),gitRepository);
        gitRepositoryRepository.patchRepository(gitRepositoryPatched);
    }

    public void deleteRepository(String name) {
        gitRepositoryRepository.deleteRepository(name);
    }

    private GitRepository merge(Optional<GitRepository> oldRepositoryOptional,GitRepository newRepository) {
        GitRepository oldRepository=oldRepositoryOptional.get();
        if(newRepository.getOwner() != null)
            oldRepository.setOwner(newRepository.getOwner());
        if(newRepository.getIssues() != 0 )
            oldRepository.setIssues(newRepository.getIssues());
        if(newRepository.getFork() != 0)
            oldRepository.setFork(newRepository.getFork());

        return oldRepository;
    }

}