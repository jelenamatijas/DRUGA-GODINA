public class SvemirskiBrod extends PilotnaLetjelica {

    public SvemirskiBrod(int id, int kolona, String navigacijskiSistem, Laser laser, Raketa raketa) {
        super(id, kolona, navigacijskiSistem, laser, raketa);
    }

    @Override
    public String toString() {
        return "SvemirskiBrod{id=" + this.id + ", red=" + this.red + ", kolona=" + this.kolona + ", navigacijskiSistem=" + this.navigacijskiSistem + ", laser=" + laser + ", raketa=" + raketa + "}"; 
    }
}