package manager;

import domain.Issue;
import domain.Issue.IssueByDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class IssueManager {
    private IssueRepository repository;

    public void addIssue(Issue issue) {
        repository.save(issue);
    }

    public ArrayList<Issue> showIssuesOpen() {
        return repository.showIssuesOpen();
    }

    public ArrayList<Issue> showIssuesClosed() {
        return repository.showIssuesClosed();
    }

    public ArrayList<Issue> filterByAuthor(String author) {
        ArrayList<Issue> filteredByAuthor = new ArrayList<>();
        Predicate<Issue> byAuthor = issue -> issue.getAuthor().equals(author);
        for (Issue issue : repository.getRepository()) {
            if (byAuthor.test(issue)) {
                filteredByAuthor.add((issue));
            }
        }
        return filteredByAuthor;
    }


    public ArrayList<Issue> filterByLabel(Set<String> issueLabels) {
        ArrayList<Issue> filteredByLabel = new ArrayList<>();
        Predicate<Issue> byLabel = issue -> issue.getIssueLabels().equals(issueLabels);
        for (Issue issue : repository.getRepository()) {
            if (byLabel.test(issue)) {
                filteredByLabel.add((issue));
            }
        }
        return filteredByLabel;
    }

    public ArrayList<Issue> filterByAssignee(Set<String> issueAssignees) {
        ArrayList<Issue> filteredByAssignee = new ArrayList<>();
        Predicate<Issue> byAssignee = issue -> issue.getIssueAssignees().equals(issueAssignees);
        for (Issue issue : repository.getRepository()) {
            if (byAssignee.test(issue)) {
                filteredByAssignee.add((issue));
            }
        }
        return filteredByAssignee;
    }

    public ArrayList<Issue> sortFromOldToNew(IssueByDate comparator) {
        ArrayList<Issue> issues = repository.getRepository();
        Collections.sort(issues, comparator);
        return issues;
    }

    public ArrayList<Issue> sortFromNewToOld(IssueByDate comparator) {
        ArrayList<Issue> issues = repository.getRepository();
        Collections.sort(issues, comparator.reversed());
        return issues;
    }

    public void updateIssueById(int id) {
        repository.update(id);
    }
}
