package fr.paris8univ.iut.csid.csidwebrepositorybase.repository.core;

public class GitRepository {
    private String name;
    private String owner;
    private int issues;
    private int fork;
    private String update;

    public GitRepository() {}

    public GitRepository(String name,String owner, int issues,int fork,String update) {
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