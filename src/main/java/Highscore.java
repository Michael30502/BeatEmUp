import processing.core.PApplet;

public class Highscore extends Menu{

    Highscore(PApplet p) {
        super(p);
        visible = false;
        Button btnBack = new Button(300,200,200,50,"Back",p);
        btnBack.addAction(new Action() {
            @Override
            public void execute() {
                visible = false;
                Main.mainMenu.visible = true;
                p.println(Main.mainMenu.visible);
                p.println(visible);
            }
        });
        btnList.add(btnBack);
    }

    @Override
    void draw() {

    }

    @Override
    void mousePushed() {

    }
}
