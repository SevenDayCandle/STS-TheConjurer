package pinacolada.cards.conjurer.series.touhouproject;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.vfx.combat.FallingIceEffect;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.PCLEffects;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Cirno extends PCLCard
{
    public static final PCLCardData DATA = register(Cirno.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Magical, PCLCardTarget.AllEnemy)
            .setDamage(3, 3)
            .setTags(PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public Cirno()
    {
        super(DATA);
    }

    @Override
    public void onPreUse(PCLUseInfo info)
    {
        PCLActions.bottom.callback(() ->
        {
            MonsterGroup monsters = AbstractDungeon.getMonsters();
            int frostCount = monsters.monsters.size() + 5;

            for (int i = 0; i < frostCount; i++)
            {
                PCLEffects.Queue.add(new FallingIceEffect(frostCount, monsters.shouldFlipVfx()));
            }

            PCLActions.top.wait(0.3f);
        });
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.ICE);
        addUseMove(PMove.applyToEnemies(2, PCLPowerHelper.Chilled, PCLPowerHelper.Shackles));
        addUseMove(PCond.onExhaust(), PMove.channelOrb(1, PCLOrbHelper.Water));
    }
}
