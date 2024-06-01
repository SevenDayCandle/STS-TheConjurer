package pinacolada.resources.conjurer;

import extendedui.EUIUtils;
import extendedui.ui.TextureCache;
import pinacolada.resources.AbstractImages;

public class ConjurerImages extends AbstractImages {
    public ConjurerImages(String id) {
        super(id);
    }

    public static class Core {
        public static final TextureCache affix = new TextureCache("images/conjurer/ui/core/Affix.png");
        public static final TextureCache elementAir = new TextureCache("images/conjurer/ui/core/Element_Air.png");
        public static final TextureCache elementDark = new TextureCache("images/conjurer/ui/core/Element_Dark.png");
        public static final TextureCache elementEarth = new TextureCache("images/conjurer/ui/core/Element_Earth.png");
        public static final TextureCache elementFire = new TextureCache("images/conjurer/ui/core/Element_Fire.png");
        public static final TextureCache elementLight = new TextureCache("images/conjurer/ui/core/Element_Light.png");
        public static final TextureCache elementWater = new TextureCache("images/conjurer/ui/core/Element_Water.png");
        public static final TextureCache squareBg = new TextureCache("images/conjurer/ui/core/SquareBG.png");
    }

    public static class Effects {
        public static final TextureCache airSlice = new TextureCache("images/conjurer/effects/AirSlice.png");
        public static final TextureCache bokeh = new TextureCache("images/conjurer/effects/Bokeh.png");
        public static final TextureCache fireBurst = new TextureCache("images/conjurer/effects/FireBurst.png");
        public static final TextureCache sparkle1 = new TextureCache("images/conjurer/effects/Sparkle1.png");
        public static final TextureCache sparkle2 = new TextureCache("images/conjurer/effects/Sparkle2.png");
        public static final TextureCache sparkle3 = new TextureCache("images/conjurer/effects/Sparkle3.png");
        public static final TextureCache sparkle4 = new TextureCache("images/conjurer/effects/Sparkle4.png");
        public static final TextureCache star = new TextureCache("images/conjurer/effects/Star.png");
        public static final TextureCache star2 = new TextureCache("images/conjurer/effects/Star2.png");
    }

/*
    public static class Monsters {
        public static final TextureCache airTrail1 = new TextureCache("images/conjurer/monsters/AirTrail1.png");
        public static final TextureCache airTrail2 = new TextureCache("images/conjurer/monsters/AirTrail2.png");
        public static final TextureCache airTrail3 = new TextureCache("images/conjurer/monsters/AirTrail3.png");
        public static final TextureCache air1 = new TextureCache("images/conjurer/monsters/Air1.png");
        public static final TextureCache air2 = new TextureCache("images/conjurer/monsters/Air2.png");
        public static final TextureCache air3 = new TextureCache("images/conjurer/monsters/Air3.png");
        public static final TextureCache chaos1 = new TextureCache("images/conjurer/monsters/Chaos1.png");
        public static final TextureCache chaos2 = new TextureCache("images/conjurer/monsters/Chaos2.png");
        public static final TextureCache chaos3 = new TextureCache("images/conjurer/monsters/Chaos3.png");
        public static final TextureCache chaosOrbital = new TextureCache("images/conjurer/monsters/ChaosOrbital.png");
        public static final TextureCache earth1 = new TextureCache("images/conjurer/monsters/Earth1.png");
        public static final TextureCache earthParticle1 = new TextureCache("images/conjurer/monsters/EarthParticle1.png");
        public static final TextureCache earthParticle2 = new TextureCache("images/conjurer/monsters/EarthParticle2.png");
        public static final TextureCache earthParticle3 = new TextureCache("images/conjurer/monsters/EarthParticle3.png");
        public static final TextureCache fire1 = new TextureCache("images/conjurer/monsters/Fire1.png");
        public static final TextureCache fire2 = new TextureCache("images/conjurer/monsters/Fire2.png");
        public static final TextureCache fire3 = new TextureCache("images/conjurer/monsters/Fire3.png");
        public static final TextureCache fireParticle1 = new TextureCache("images/conjurer/monsters/FireParticle1.png");
        public static final TextureCache fireParticle2 = new TextureCache("images/conjurer/monsters/FireParticle2.png");
        public static final TextureCache fireParticle3 = new TextureCache("images/conjurer/monsters/FireParticle3.png");
        public static final TextureCache water1 = new TextureCache("images/conjurer/monsters/Water1.png");
        public static final TextureCache water2 = new TextureCache("images/conjurer/monsters/Water2.png");
        public static final TextureCache water3 = new TextureCache("images/conjurer/monsters/Water3.png");
        public static final TextureCache water4 = new TextureCache("images/conjurer/monsters/Water4.png");
    }
*/

    public static class Tutorial {
        public static final TextureCache etut01 = new TextureCache("images/conjurer/ui/tutorial/etut01.png");
        public static final TextureCache etut02 = new TextureCache("images/conjurer/ui/tutorial/etut02.png");
        public static final TextureCache etut03 = new TextureCache("images/conjurer/ui/tutorial/etut03.png");
    }

    // No background
    @Override
    public String getCharBackgroundPath() {
        return EUIUtils.EMPTY_STRING;
    }
}
