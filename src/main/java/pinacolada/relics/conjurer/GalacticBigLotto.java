package pinacolada.relics.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class GalacticBigLotto extends PCLRelic {
    public static final PCLRelicData DATA = register(GalacticBigLotto.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SHOP, LandingSound.MAGICAL);

    public GalacticBigLotto() {
        super(DATA);
    }

    @Override
    public String getDescriptionImpl() {
        return this.formatDescription(0, this.getValue(), this.getLossPercent());
    }

    @Override
    public int getValue() {
        return 10;
    }

    public int getLossPercent() {
        return 50;
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        super.onEnterRoom(room);

        if (!usedUp && GameUtilities.chance(getValue())) {
            AbstractRelic relic = AbstractDungeon.returnRandomNonCampfireRelic(AbstractDungeon.returnRandomRelicTier());
            if (relic != null && !(relic instanceof Circlet)) {
                GameUtilities.obtainRelicFromEvent(relic);
            }

            if (GameUtilities.chance(getValue())) {
                AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, MathUtils.floor(AbstractDungeon.player.currentHealth * getLossPercent() / 100f)));
                usedUp();
            }

            flash();
        }

    }
}