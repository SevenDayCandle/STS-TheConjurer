package pinacolada.relics.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import pinacolada.annotations.VisibleRelic;
import pinacolada.effects.PCLEffects;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class GalacticBigLotto extends PCLRelic {
    public static final PCLRelicData DATA = register(GalacticBigLotto.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SHOP, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.honkai);

    public GalacticBigLotto() {
        super(DATA);
    }

    @Override
    public String getDescriptionImpl() {
        return this.formatDescription(0, this.getValue(), this.getBadPercent(), this.getLossPercent());
    }

    public int getLossPercent() {
        return 70;
    }

    public int getBadPercent() {
        return 20;
    }

    @Override
    public int getValue() {
        return 15;
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        super.onEnterRoom(room);

        if (!usedUp && GameUtilities.chance(getValue())) {
            AbstractRelic relic = AbstractDungeon.returnRandomNonCampfireRelic(AbstractDungeon.returnRandomRelicTier());
            if (relic != null && !(relic instanceof Circlet)) {
                PCLEffects.Queue.obtainRelic(relic);
                flash();
            }

            if (GameUtilities.chance(getValue())) {
                AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, MathUtils.floor(AbstractDungeon.player.currentHealth * getLossPercent() / 100f)));
                usedUp();
            }
        }

    }
}