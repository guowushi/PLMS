
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.l2fprod.common.propertysheet.DefaultProperty;
import com.l2fprod.common.propertysheet.PropertySheetPanel;

/**
 * An example that creates a l2fprod PropertySheetPanel that displays any
 * Collection.
 */
public class l2fprod<C> extends PropertySheetPanel {

    // Choose some bean. An animal as example.
    static class Animal {
        private String name;
        private String family;

        public Animal(String name, String family) {
            this.name = name;
            this.family = family;
        }

        @Override public String toString() {
            return name + " " + family;
        }
    }

    /**
     * @param simpleModel The input collection as data model.
     */
    public l2fprod(Collection<C> simpleModel) {
        super();
        populateCollectionProperties(simpleModel);
    }

    private void populateCollectionProperties(Collection<C> collection) {
        int index = 0;
        for (C entry : collection) {
            // Define property properties
            DefaultProperty property = new DefaultProperty();
            property.setDisplayName(entry.getClass().getSimpleName() + "[" + index++ +"]");
            property.setValue(entry.toString());
            // Set any other properties ...
            // and add.
            addProperty(property);
        }
    }

    // Start me here!
    public static void main(String[] args) {
        // Inside EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                JFrame frame = new JFrame("A simple example...");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new l2fprod<Animal>(getAnimals()));
                frame.pack();
                frame.setVisible(true);
            }

            private Collection<Animal> getAnimals() {
                Collection<Animal> animals = new ArrayList<Animal>();
                animals.add(new Animal("Lion", "Felidae"));
                animals.add(new Animal("Duck", "Anatidae"));
                animals.add(new Animal("Cat", "Felidae"));
                return animals;
            }
        });
    }

}
