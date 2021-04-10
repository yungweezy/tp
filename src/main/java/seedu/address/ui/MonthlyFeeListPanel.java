package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.FeeUtil;
import seedu.address.model.Year;
import seedu.address.model.fee.Month;
import seedu.address.model.fee.MonthlyFee;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of monthly fees for this month and the previous 2 month.
 */
public class MonthlyFeeListPanel extends UiPart<Region> {
    private static final String FXML = "MonthlyFeeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MonthlyFeeListPanel.class);
    private ObservableList<MonthlyFee> monthlyFeeList;

    @FXML
    private ListView<MonthlyFee> monthlyFeeListView;

    /**
     * Creates a {@code MonthlyFeeListPanel} with the given {@code ObservableList}.
     */
    public MonthlyFeeListPanel(ObservableList<Student> studentList) {
        super(FXML);
        monthlyFeeList = FXCollections.observableArrayList();

        populateMonthlyFeeList(studentList);
        setStudentListListener(studentList);
        populateMonthlyFeeListView();
    }

    /**
     * Set the student list to list for changes and update the monthly fee list accordingly.
     * @param studentList Student list to listen for change.
     */
    private void setStudentListListener(ObservableList<Student> studentList) {
        studentList.addListener((ListChangeListener<Student>) change -> {
            while (change.next()) {
                monthlyFeeList.clear();
                populateMonthlyFeeList(studentList);
                populateMonthlyFeeListView();
            }
        });
    }

    /**
     * Populates the MonthlyFeeListViewCell with the given {@code ObservableList}.
     */
    private void populateMonthlyFeeListView() {
        monthlyFeeListView.setItems(monthlyFeeList);
        monthlyFeeListView.setCellFactory(listView -> new MonthlyFeeListViewCell());
    }

    /**
     * Populates the monthlyFeeList with {@code MonthlyFee} for the current month + previous 2 months.
     * @param studentList Student list to listen for change.
     */
    private void populateMonthlyFeeList(ObservableList<Student> studentList) {
        LocalDateTime currMonthYear;
        LocalDateTime nextMonthYear;

        LocalDateTime now = LocalDateTime.now();
        Month month = new Month(now.getMonth().getValue());
        Year year = new Year(now.getYear());
        currMonthYear = DateUtil.getFirstDayOfMonth(month, year);
        nextMonthYear = currMonthYear.plusMonths(1);

        // Get fees for the current month + previous 2 months.
        for (int i = 0; i < 3; i++) {
            // Get current month value and add the result to the resulting string
            double currMonthlyFee = getMonthFeeByPeriod(studentList, currMonthYear, nextMonthYear);
            monthlyFeeList.add(new MonthlyFee(currMonthlyFee, new Month(currMonthYear.getMonth().getValue()),
                new Year(currMonthYear.getYear())));
            // Update to previous month
            currMonthYear = currMonthYear.minusMonths(1);
            nextMonthYear = currMonthYear.plusMonths(1);
        }
    }

    /**
     * Gets the current month fee based on the start period and end period.
     *
     * @param studentList List of students.
     * @param startPeriod Start Period of the month.
     * @param endPeriod End Period of the month.
     * @return Monthly fee for the current month.
     */
    private double getMonthFeeByPeriod(ObservableList<Student> studentList, LocalDateTime startPeriod,
        LocalDateTime endPeriod) {
        double fee = 0;
        for (Student student : studentList) {
            fee += FeeUtil.getFeePerStudent(student, startPeriod, endPeriod);
        }
        return fee;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code MonthlyFee} using a {@code MonthlyFeeCard}.
     */
    static class MonthlyFeeListViewCell extends ListCell<MonthlyFee> {
        @Override
        protected void updateItem(MonthlyFee monthlyFee, boolean empty) {
            super.updateItem(monthlyFee, empty);

            if (empty || monthlyFee == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MonthlyFeeCard(monthlyFee).getRoot());
            }
        }
    }
}
