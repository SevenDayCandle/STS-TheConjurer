package pinacolada.resources.conjurer;

import extendedui.ui.TextureCache;
import pinacolada.resources.PCLImages;

public class ConjurerImages extends PCLImages {
    public final Core core = new Core();
    public final Monsters monsters = new Monsters();

    public ConjurerImages(String id) {
        super(id);
    }

    public static class Core {
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
        public final TextureCache fireExternal = new TextureCache("images/conjurer/monsters/FireExternal.png");
        public final TextureCache fireExternal2 = new TextureCache("images/conjurer/monsters/FireExternal2.png");
        public final TextureCache fireInternal = new TextureCache("images/conjurer/monsters/FireInternal.png");
        public final TextureCache water1 = new TextureCache("images/conjurer/monsters/Water1.png");
        public final TextureCache water2 = new TextureCache("images/conjurer/monsters/Water2.png");
        public final TextureCache water3 = new TextureCache("images/conjurer/monsters/Water3.png");
        public final TextureCache water4 = new TextureCache("images/conjurer/monsters/Water4.png");
    }

}
