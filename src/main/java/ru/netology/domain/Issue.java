package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.function.Predicate;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class Issue {
    private int id;
    private boolean open;
    private String author;
    private String name;
    private Set<String> issueLabels;
    private Set<String> issueAssignees;
    private Date creationDate;


    public static class IssueByDate implements Comparator<Issue> {
        public int compare(Issue o1, Issue o2) {
            return (int) (o1.getCreationDate().getTime() - o2.getCreationDate().getTime());
        }
    }

}

