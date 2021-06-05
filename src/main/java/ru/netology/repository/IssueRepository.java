package repository;

import domain.Issue;
import exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class IssueRepository {
    ArrayList<Issue> repository = new ArrayList<Issue>();

    public ArrayList<Issue> findAll() {
        return repository;
    }

    public void save(Issue issue) {
        repository.add(issue);
    }

    public ArrayList<Issue> showIssuesOpen() {
        ArrayList<Issue> openIssues = new ArrayList<>();
        for (Issue issue : repository) {
            if (issue.isOpen() == true) {
                openIssues.add(issue);
            }
        }
        return openIssues;
    }

    public ArrayList<Issue> showIssuesClosed() {
        ArrayList<Issue> closedIssues = new ArrayList<>();
        for (Issue issue : repository) {
            if (issue.isOpen() == false) {
                closedIssues.add(issue);
            }
        }
        return closedIssues;
    }

    public Issue findById(int id) {
        for (Issue issue : repository) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public void update(int id) {
        for (Issue issue : repository) {
            if (findById(id) == null) {
                throw new NotFoundException("Issue with id: " + id + " not found");
            }
            if (issue.getId() == id) {
                if (issue.isOpen() == true) {
                    issue.setOpen(false);
                } else {
                    issue.setOpen(true);
                }
            }
        }

    }
}
