package fr.paris8univ.iut.csid.csidwebrepositorybase.repository.dao;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "repository")
public class GitRepositoryEntity {
    @Id
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "owner")
    private String owner;

    @Column(name = "issues")
    private int issues;

    @Column(name = "fork")
    private int fork;

    @Column(name = "update")
    private String update;


/*  @Temporal(value= TemporalType.TIMESTAMP)
    @Column(name="lastUpdate")
    private Date lastUpdate;
 */


    public GitRepositoryEntity() {}

    public GitRepositoryEntity(String name,String owner, int issues,int fork,String update) {
        this.name=name;
        this.owner=owner;
        this.issues=issues;
        this.fork=fork;
        this.update=update;
    }

    public int getIssues() {
        return issues;
    }

    public void setIssues(int issues) {
        this.issues = issues;
    }

    public int getFork() {
        return fork;
    }

    public void setFork(int fork) {
        this.fork = fork;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner=owner;
    }

   public String getUpdate() {
        return this.update;
    }

    public void setUpdate(String update) {
        this.update=update;
    }


}