package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tuition.Tuition;

/**
 * Panel containing the list of persons.
 */
public class TuitionListPanel extends UiPart<Region> {
    private static final String FXML = "TuitionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TuitionListPanel.class);

    @FXML
    private ListView<Tuition> tuitionListView;

    /**
     * Creates a {@code TuitionListPanel} with the given {@code ObservableList}.
     */
    public TuitionListPanel(ObservableList<Tuition> tuitionList) {
        super(FXML);
        tuitionListView.setItems(tuitionList);
        tuitionListView.setCellFactory(listView -> new TuitionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class TuitionListViewCell extends ListCell<Tuition> {
        @Override
        protected void updateItem(Tuition tuition, boolean empty) {
            super.updateItem(tuition, empty);

            if (empty || tuition == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TuitionCard(tuition, getIndex() + 1).getRoot());
            }
        }
    }

}
