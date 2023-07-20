package pinacolada.resources.conjurer;

import extendedui.ui.TextureCache;
import pinacolada.resources.AbstractImages;

public class ConjurerImages extends AbstractImages {
    public final Core core = new Core();
    public final Monsters monsters = new Monsters();
    public final Tutorial tutorial = new Tutorial();

    public ConjurerImages(String id) {
        super(id);
    }

    public static class Core {
        public final TextureCache bokeh = new TextureCache("images/conjurer/ui/core/Bokeh.png");
        public final TextureCache elementAir = new TextureCache("images/conjurer/ui/core/Element_Air.png");
        public final TextureCache elementDark = new TextureCache("images/conjurer/ui/core/Element_Dark.png");
        public final TextureCache elementEarth = new TextureCache("images/conjurer/ui/core/Element_Earth.png");
        public final TextureCache elementFire = new TextureCache("images/conjurer/ui/core/Element_Fire.png");
        public final TextureCache elementLight = new TextureCache("images/conjurer/ui/core/Element_Light.png");
        public final TextureCache elementWater = new TextureCache("images/conjurer/ui/core/Element_Water.png");
        public final TextureCache squareBg = new TextureCache("images/conjurer/ui/core/SquareBG.png");
    }

    public static class Monsters {
        public final TextureCache airCloud1 = new TextureCache("images/conjurer/monsters/AirCloud1.png");
        public final TextureCache airCloud2 = new TextureCache("images/conjurer/monsters/AirCloud2.png");
        public final TextureCache air1 = new TextureCache("images/conjurer/monsters/Air1.png");
        public final TextureCache air2 = new TextureCache("images/conjurer/monsters/Air2.png");
        public final TextureCache air3 = new TextureCache("images/conjurer/monsters/Air3.png");
        public final TextureCache chaos1 = new TextureCache("images/conjurer/monsters/Chaos1.png");
        public final TextureCache chaos2 = new TextureCache("images/conjurer/monsters/Chaos2.png");
        public final TextureCache chaos3 = new TextureCache("images/conjurer/monsters/Chaos3.png");
        public final TextureCache chaosOrbital = new TextureCache("images/conjurer/monsters/ChaosOrbital.png");
        public final TextureCache earth1 = new TextureCache("images/conjurer/monsters/Earth1.png");
        public final TextureCache fire1 = new TextureCache("images/conjurer/monsters/Fire1.png");
        public final TextureCache fire2 = new TextureCache("images/conjurer/monsters/Fire2.png");
        public final TextureCache fire3 = new TextureCache("images/conjurer/monsters/Fire3.png");
        public final TextureCache water1 = new TextureCache("images/conjurer/monsters/Water1.png");
        public final TextureCache water2 = new TextureCache("images/conjurer/monsters/Water2.png");
        public final TextureCache water3 = new TextureCache("images/conjurer/monsters/Water3.png");
        public final TextureCache water4 = new TextureCache("images/conjurer/monsters/Water4.png");
    }

    public static class Tutorial {
        public final TextureCache afftut01 = new TextureCache("images/conjurer/ui/tutorial/afftut01.png");
        public final TextureCache etut01 = new TextureCache("images/conjurer/ui/tutorial/etut01.png");
        public final TextureCache etut02 = new TextureCache("images/conjurer/ui/tutorial/etut02.png");
        public final TextureCache etut03 = new TextureCache("images/conjurer/ui/tutorial/etut03.png");
        public final TextureCache ctut01 = new TextureCache("images/conjurer/ui/tutorial/ctut01.png");
        public final TextureCache ctut02 = new TextureCache("images/conjurer/ui/tutorial/ctut02.png");
        public final TextureCache ctut03 = new TextureCache("images/conjurer/ui/tutorial/ctut03.png");
        public final TextureCache ctut04 = new TextureCache("images/conjurer/ui/tutorial/ctut04.png");
        public final TextureCache ctut05 = new TextureCache("images/conjurer/ui/tutorial/ctut05.png");
        public final TextureCache ctut06 = new TextureCache("images/conjurer/ui/tutorial/ctut06.png");
    }

}
