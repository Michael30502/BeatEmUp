import processing.core.PApplet;

public class Infoscreen extends Menu{

PApplet p;

    Infoscreen(PApplet p) {
        super(p);
        this.p = p;
        visible = false;
        Button btnBack = new Button(400,200,200,50,"Back",p);
        btnBack.addAction(new Action() {
            @Override
            public void execute() {
                visible = false;
                Main.mainMenu.visible = true;
             //   p.println(Main.mainMenu.visible);
              //  p.println(visible);
            }
        });
        btnList.add(btnBack);
    }


    @Override
    void draw() {
        p.textSize(15);
        p.text("move on wasd or the arrow keys",500,300);
        p.text("punch on j or z, kick on k or x, and use special move on l or c in the end of a combo",500,350);
        p.text("use your special mode on o or b, if the special bar is full",500,400);
        p.text ("dash on i or spacebar",500,450);
        p.text("block attacks on u or v",500,500);
        p.textSize(12);

    }

    @Override
    void mousePushed() {

    }
}
