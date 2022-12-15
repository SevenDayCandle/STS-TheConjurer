package pinacolada.cards.conjurer.series.genshinimpact;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.interfaces.delegates.ActionT0;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.cards.conjurer.colorless.SheerCold;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.interfaces.markers.PCondWithoutCheck;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.common.DelayedDamagePower;
import pinacolada.powers.conjurer.ChilledPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.DelayUse;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PCustomCond;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.skills.skills.base.conditions.PCond_Info;
import pinacolada.utilities.GameUtilities;

public class AyakaKamisato extends PCLCard
{
    public static final PCLCardData DATA = register(AyakaKamisato.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Brutal)
            .setDamage(13, 2, 2)
            .setTags(PCLCardTag.Ethereal, PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);
    private static final Color FADE_EFFECT_COLOR = new Color(0.6f, 0.6f, 0.8f, 0.5f);

    public AyakaKamisato()
    {
        super(DATA);
    }

    @Override
    public void onPreUse(PCLUseInfo info)
    {
/*        GameEffects.Queue.roomTint(Color.BLACK, 0.8F);
        SFX.play(SFX.ATTACK_SCYTHE, 0.75f, 0.75f);
        SFX.play(SFX.ATTACK_SCYTHE, 0.55f, 0.55f, 0.75f);
        GameEffects.Queue.add(new AdditiveSlashImpactEffect(m.hb.cX - 100f * Settings.scale, m.hb.cY + 100f * Settings.scale, Color.WHITE.cpy()));
        GameEffects.Queue.add(new AnimatedSlashEffect(m.hb.cX, m.hb.cY + 20f * Settings.scale,
                -500f, 0f, 260f, 8f, Color.LIGHT_GRAY.cpy(), Color.WHITE.cpy()));
        float wait = GameEffects.Queue.add(new AnimatedSlashEffect(m.hb.cX, m.hb.cY + 20f * Settings.scale,
                500f, 0f, 80f, 8f, Color.LIGHT_GRAY.cpy(), Color.WHITE.cpy())).duration * 6f;
        for (int i = 0; i < 4; i++)
        {
            GameActions.Top.wait(0.2f);
            GameEffects.Queue.add(new AnimatedSlashEffect(m.hb.cX - i * 10f * Settings.scale, m.hb.cY + 20f * Settings.scale,
                    500f, 0f, 80f, 8f, FADE_EFFECT_COLOR, FADE_EFFECT_COLOR));
        }
        GameActions.Top.wait(wait);*/
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.SWORD27, Color.SKY);
        addUseMove(PMove.gain(3, PCLPowerHelper.SelfImmolation), getSpecialMove(0, this::action, 1));
        addUseMove(PCond.limited(), new AyakaKamisatoCond(DATA, 15), PMove.playCopy(1, PCLCardTarget.Self, SheerCold.DATA.ID));
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        int amount = GameUtilities.getPowerAmount(info.target, ChilledPower.POWER_ID) * 2;
        if (amount > 0)
        {
            PCLActions.bottom.applyPower(info.source, info.target, PCLCardTarget.Single, PCLPowerHelper.Chilled, amount);
        }
    }


    protected static class AyakaKamisatoCond extends PCustomCond implements PCondWithoutCheck
    {
        public AyakaKamisatoCond(PCLCardData data, int amount)
        {
            super(data, 1, amount);
        }

        @Override
        public void use(PCLUseInfo info)
        {
            if (childEffect != null)
            {
                new DelayUse(amount, DelayUse.Timing.EndOfTurnFirst, info, (i) -> this.childEffect.use(i)).start();
            }
        }

        public void use(PCLUseInfo info, int index)
        {
            if (childEffect != null)
            {
                new DelayUse(amount, DelayUse.Timing.EndOfTurnFirst, info, (i) -> this.childEffect.use(i, index)).start();
            }
        }

        public void use(PCLUseInfo info, boolean isUsing)
        {
            if (isUsing && childEffect != null)
            {
                new DelayUse(amount, DelayUse.Timing.EndOfTurnFirst, info, (i) -> this.childEffect.use(i)).start();
            }
        }

        protected void useImpl(PCLUseInfo info, ActionT0 callback)
        {
            for (AbstractCreature c : GameUtilities.getAllCharacters(true))
            {
                if ((GameUtilities.getPowerAmount(c, DelayedDamagePower.POWER_ID) >= amount || GameUtilities.getPowerAmount(c, ChilledPower.POWER_ID) >= amount)
                    && (!(parent instanceof PCond_Info) || ((PCond_Info) parent).tryActivate(info)))
                {
                    callback.invoke();
                    return;
                }
            }
        }
    }
}
