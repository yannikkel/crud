package manager;

import domain.Issue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.IssueRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class IssueManagerTest {

    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);
    private String label1 = "theme: documentation";
    private String label2 = "theme: extensions";
    private String label3 = "theme: concurrency";
    private String label4 = "component: Jupiter";
    private String label5 = "component: Platform";
    private Set<String> setOfLabels1 = Set.of(label1, label4);
    private Set<String> setOfLabels2 = Set.of(label2, label5);
    private Set<String> setOfLabels3 = Set.of(label3);
    private Set<String> setOfLabels4 = Set.of(label1, label5);
    private String assignee1 = "Sasha";
    private String assignee2 = "Masha";
    private String assignee3 = "Pasha";
    private Set<String> setOfAssignees1 = Set.of(assignee1, assignee2);
    private Set<String> setOfAssignees2 = Set.of(assignee2, assignee3);
    private Set<String> setOfAssignees3 = Set.of(assignee3);
    private Date date1 = new Date(1_616_080_320_000L);
    private Date date2 = new Date(1_615_545_720_000L);
    private Date date3 = new Date(1_615_400_460_000L);
    private Date date4 = new Date(1_616_269_980_000L);
    private Date date5 = new Date(1_616_315_820_000L);
    private Issue issue1 = new Issue(1, true, "Sasha", "Documentation for Jupiter", setOfLabels1, setOfAssignees1, date1);
    private Issue issue2 = new Issue(2, true, "Masha", "Platform extensions", setOfLabels2, setOfAssignees2, date2);
    private Issue issue3 = new Issue(3, false, "Pasha", "Problem with concurrency", setOfLabels3, setOfAssignees3, date3);
    private Issue issue4 = new Issue(4, true, "Sasha", "Documentation for platform", setOfLabels4, setOfAssignees1, date4);
    private Issue issue5 = new Issue(5, false, "Sasha", "Documentation for Jupiter again", setOfLabels1, setOfAssignees3, date5);

    @BeforeEach
    public void setUp() {
        manager.addIssue(issue1);
        manager.addIssue(issue2);
        manager.addIssue(issue3);
        manager.addIssue(issue4);
        manager.addIssue(issue5);
    }


    @Test
    void shouldFilterByAuthor() {
        ArrayList<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue4);
        expected.add(issue5);
        ArrayList<Issue> actual = manager.filterByAuthor("Sasha");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        ArrayList<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue5);
        ArrayList<Issue> actual = manager.filterByLabel(setOfLabels1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssignee() {
        ArrayList<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue4);
        ArrayList<Issue> actual = manager.filterByAssignee(setOfAssignees1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortFromOldToNew() {
        ArrayList<Issue> expected = new ArrayList<>();
        expected.add(issue3);
        expected.add(issue2);
        expected.add(issue1);
        expected.add(issue4);
        expected.add(issue5);
        Issue.IssueByDate comparator = new Issue.IssueByDate();
        ArrayList<Issue> actual = manager.sortFromOldToNew(comparator);
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortFromNewToOld() {
        ArrayList<Issue> expected = new ArrayList<>();
        expected.add(issue5);
        expected.add(issue4);
        expected.add(issue1);
        expected.add(issue2);
        expected.add(issue3);
        Issue.IssueByDate comparator = new Issue.IssueByDate();
        ArrayList<Issue> actual = manager.sortFromNewToOld(comparator);
        assertEquals(expected, actual);
    }
}
