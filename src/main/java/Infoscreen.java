import processing.core.PApplet;

public class Infoscreen extends Menu{

PApplet p;

    Infoscreen(PApplet p) {
        super(p);
        this.p = p;
        Button btnBack = new Button(300,200,200,50,"Back",p);
        btnBack.addAction(new Action() {
            @Override
            public void execute() {
                visible = false;
                Main.mainMenu.visible = true;
            }
        });
        btnList.add(btnBack);
    }


    @Override
    void draw() {
        p.text("move on wasd or the arrow keys",450,450);
        p.text("punch on j, kick on k, and use special move on l",450,500);
    }

    @Override
    void mousePushed() {

    }
}
