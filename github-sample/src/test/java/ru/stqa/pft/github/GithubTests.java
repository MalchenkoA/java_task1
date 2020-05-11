package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("091271c592dbfdd735fcd296f2dfb6d60d882a5b");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("MalchenkoA","java_task1")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String>().build()) ){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
