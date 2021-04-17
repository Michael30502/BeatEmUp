import processing.core.PApplet;

public class Credits extends Menu{
    PApplet p;
    Credits(PApplet p) {
        super(p);
        this.p = p;
        visible = false;
        Button btnBack = new Button(400,200,200,50,"Back",p);
        btnBack.addAction(new Action() {
            @Override
            public void execute() {
                visible = false;
                Main.mainMenu.visible = true;
               // p.println(Main.mainMenu.visible);
              //  p.println(visible);
            }
        });
        btnList.add(btnBack);
    }


    @Override
    void draw() {

        p.text("Michael Sylvest Bendtsen - Programmerede en del",500,350);
        p.text("Marius Stokkebro - Programmerede også en del",500,400);
    }

    @Override
    void mousePushed() {

    }
}
