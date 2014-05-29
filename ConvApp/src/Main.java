public class Main {
    static ThreeForm tf;

    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    tf = new ThreeForm();
                    tf.CreateAndShowGUI();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
