package pinacolada.cards.conjurer.series.touhouproject;


import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PCustomMod;

@VisibleCard
public class EirinYagokoro extends PCLCard
{
    public static final PCLCardData DATA = register(EirinYagokoro.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial)
            .setDamage(2, 0)
            .setHp(5, 1)
            .setAffinities(1, PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.touhouProject, true);

    public EirinYagokoro()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.POISON).setChain(new EirinYagokoroMod(DATA, 1), PTrait.damage(2).setUpgrade(1));
        addUseMove(PCond.onSummon(), PMove.gainTempHP(PCLCardTarget.None, 3).setUpgrade(1));
    }

    protected static class EirinYagokoroMod extends PCustomMod
    {
        public EirinYagokoroMod(PCLCardData data, int amount)
        {
            super(data, 0, amount);
        }

        @Override
        public int getModifiedAmount(PSkill<?> be, PCLUseInfo info)
        {
            return EUIUtils.count(AbstractDungeon.player.potions, po -> po != null && po.canUse());
        }
    }
}
