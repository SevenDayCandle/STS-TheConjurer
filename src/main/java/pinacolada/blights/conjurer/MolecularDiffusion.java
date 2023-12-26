package pinacolada.blights.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleBlight;
import pinacolada.blights.PCLBlight;
import pinacolada.blights.PCLBlightData;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardAffinities;
import pinacolada.dungeon.CombatManager;
import pinacolada.interfaces.subscribers.OnCardUsingSubscriber;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.RandomizedList;

@VisibleBlight
public class MolecularDiffusion extends PCLBlight {
    public static final PCLBlightData DATA = register(MolecularDiffusion.class, ConjurerResources.conjurer);

    public MolecularDiffusion() {
        super(DATA);
    }
}