package pinacolada.skills.conjurer.conditions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.EUIRM;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerEnum;
import pinacolada.skills.PCond;
import pinacolada.skills.PSkillData;
import pinacolada.skills.PSkillSaveData;
import pinacolada.skills.fields.PField_Affinity;

public class PCond_Intensify extends PCond<PField_Affinity>
{

    public static final PSkillData<PField_Affinity> DATA = register(PCond_Intensify.class, PField_Affinity.class, 1, 1)
            .setColors(ConjurerEnum.Cards.THE_CONJURER)
            .selfTarget();

    public PCond_Intensify()
    {
        this((PCLAffinity) null);
    }

    public PCond_Intensify(PCLAffinity... affinities)
    {
        super(DATA, PCLCardTarget.None, 0);
        fields.setAffinity(affinities);
    }

    public PCond_Intensify(PSkillSaveData content)
    {
        super(DATA, content);
    }

    // This should not activate the child effect when played normally

    @Override
    public String getSubText()
    {
        return TEXT.conditions.wheneverYou(fields.affinities.isEmpty() ? PGR.core.tooltips.level.title : EUIRM.strings.verbNoun(PGR.core.tooltips.level.title, fields.getAffinityLevelOrString()));
    }

    @Override
    public void use(PCLUseInfo info)
    {
    }

    @Override
    public void use(PCLUseInfo info, int index)
    {
    }

    @Override
    public boolean canPlay(AbstractCard card, AbstractMonster m)
    {
        return true;
    }

    @Override
    public boolean triggerOnIntensify(PCLAffinity affinity)
    {
        if (fields.affinities.isEmpty() || fields.affinities.contains(affinity))
        {
            if (this.childEffect != null)
            {
                this.childEffect.use(makeInfo(null).setData(affinity));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCondition(PCLUseInfo info, boolean isUsing, boolean fromTrigger)
    {
        return false;
    }
}
