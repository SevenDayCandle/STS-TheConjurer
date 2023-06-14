package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.exordium.*;
import extendedui.EUIInputManager;
import extendedui.EUIUtils;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.conjurer.colorless.Iridescence;
import pinacolada.cards.pcl.status.Status_Wound;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PDelay;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class DisguisePropBox extends PCLRelic {
    public static final PCLRelicData DATA = register(DisguisePropBox.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.MAGICAL);
    protected transient PSkill<?> monsterSkill;

    public DisguisePropBox() {
        super(DATA);
    }

    @Override
    public void update() {
        super.update();

        if (GameUtilities.inBattle() && this.hb.hovered && EUIInputManager.rightClick.isJustPressed()) {
            selectCreatureForTransform().addCallback(() -> {
                monsterSkill = getSkillForMonster();
            });
        }
    }

    public String getUpdatedDescription() {
        String desc = super.getUpdatedDescription();
        return monsterSkill != null ? EUIUtils.joinStrings(EUIUtils.SPLIT_LINE, desc, formatDescription(1, monsterSkill.getPowerText())) : desc;
    }

    @Override
    protected void activateBattleEffect() {
        monsterSkill = getSkillForMonster();
        if (monsterSkill != null) {
            monsterSkill.use(new PCLUseInfo(null, player, null));
        }
    }

    public PSkill<?> getSkillForMonster() {
        if (PGR.dungeon.currentForm == null) {
            return null;
        }
        switch (PGR.dungeon.currentForm) {
            case AcidSlime_L.ID:
            case AcidSlime_M.ID:
            case AcidSlime_S.ID:
                return PMove.applyToEnemies(1, PCLPowerHelper.Weak);
            case BanditBear.ID: return PMove.applyToEnemies(3, PCLPowerHelper.Bruised);
            case BanditLeader.ID: return PMove.applyToRandom(3, PCLPowerHelper.Weak);
            case BanditPointy.ID:
            case GremlinLeader.ID:
                return PMove.dealDamageToRandom(10, PCLAttackVFX.SLASH_DIAGONAL.key);
            case BookOfStabbing.ID: return PMultiSkill.join(PMove.dealDamageToAll(12, PCLAttackVFX.SLASH_DIAGONAL.key), PMove.createDrawPile(1, Status_Wound.DATA.ID));
            case BronzeAutomaton.ID: return PDelay.turnStart(4).setChild(PMultiSkill.join(PMove.dealDamageToAll(40, PCLAttackVFX.LIGHTNING.key), PMove.applyToEveryone(2, PCLPowerHelper.Vulnerable)));
            case Byrd.ID: return PMove.gainTemporaryPlayer(2, PCLPowerHelper.Flight);
            case Centurion.ID: return PMove.gainBlock(PCLCardTarget.None, 10);
            case Champ.ID:
            case ShelledParasite.ID:
                return PMove.gainPlayer(4, PCLPowerHelper.PlatedArmor);
            case Chosen.ID:
            case GremlinFat.ID:
                return PMove.applyToRandom(4, PCLPowerHelper.Weak);
            case Cultist.ID: return PMove.gainPlayer(1, PCLPowerHelper.Ritual);
            case Exploder.ID: return PDelay.turnStart(2).setChild(PMove.applyToEnemies(14, PCLElementHelper.Blasted));
            case FungiBeast.ID: return PMove.applyToEnemies(1, PCLPowerHelper.Vulnerable);
            case GiantHead.ID: return PMove.applyToRandom(1, PCLPowerHelper.Slow);
            case GremlinNob.ID: return PMove.gainPlayer(2, PCLPowerHelper.Strength);
            case GremlinThief.ID:
            case TorchHead.ID:
                return PMove.dealDamage(9, PCLAttackVFX.SLASH_HORIZONTAL.key);
            case GremlinTsundere.ID: return PDelay.turnStart(1).setChild(PMove.gainBlock(PCLCardTarget.Team, 7));
            case GremlinWarrior.ID: return PMove.gainPlayer(5, PCLPowerHelper.Vigor);
            case GremlinWizard.ID: return PDelay.turnStart(2).setChain(PMove.applyToTeam(2, PCLPowerHelper.Sorcery));
            case Healer.ID: return PMove.gainPlayer(1, PCLPowerHelper.Regen);
            case Hexaghost.ID: return PMove.applyToEnemies(7, PCLElementHelper.Ignis);
            case JawWorm.ID: return PMultiSkill.join(PMove.gainBlockPlayer(3), PMove.dealDamage(3, PCLAttackVFX.BLUNT_LIGHT.key));
            case Lagavulin.ID: return PMove.gainPlayer(2, PCLPowerHelper.Metallicize);
            case Looter.ID:
            case Mugger.ID:
                return PMove.gainGold(4);
            case LouseDefensive.ID:
            case LouseNormal.ID:
                return PMove.gainPlayer(6, PCLPowerHelper.CurlUp);
            case Nemesis.ID: return PMove.gainPlayer(1, PCLPowerHelper.Intangible);
            case OrbWalker.ID: return CMove.gainMatter(10);
            case Repulsor.ID: return PMove.draw(1);
            case Reptomancer.ID: return PMove.applyToRandom(10, PCLPowerHelper.Poison);
            case Sentry.ID: return PMove.gainPlayer(1, PCLPowerHelper.Artifact);
            case SlaverBlue.ID: return PMove.applyToRandom(3, PCLPowerHelper.Shackles);
            case SlaverRed.ID: return PMove.applyToRandom(3, PCLPowerHelper.Vulnerable);
            case SlimeBoss.ID: return PMove.applyToRandom(38, PCLPowerHelper.DelayedDamage);
            case SnakeDagger.ID: return PMove.applyToEnemies(3, PCLPowerHelper.Poison);
            case SnakePlant.ID: return PMove.gainPlayer(3, PCLPowerHelper.Malleable);
            case Snecko.ID: return PMove.create(Iridescence.DATA.ID);
            case SphericGuardian.ID: return PMove.gainPlayer(1, PCLPowerHelper.Blur);
            case SpikeSlime_L.ID:
            case SpikeSlime_M.ID:
            case SpikeSlime_S.ID: return PMove.applyToEnemies(2, PCLPowerHelper.Blinded);
            case Spiker.ID: return PMove.gainPlayer(3, PCLPowerHelper.Thorns);
            case Taskmaster.ID: return PMultiSkill.join(PMove.dealDamage(9, PCLAttackVFX.SLASH_DIAGONAL.key), PMove.applyToRandom(1, PCLPowerHelper.Vulnerable));
            case TimeEater.ID: return PMove.applyToEveryone(1, PCLPowerHelper.Silenced);
            case TheCollector.ID: return PMove.applyToRandom(3, PCLPowerHelper.Vulnerable, PCLPowerHelper.Weak, PCLPowerHelper.Frail);
            case TheGuardian.ID: return PMultiSkill.join(PMove.gainPlayer(3, PCLPowerHelper.Thorns), PMove.gainPlayer(10, PCLPowerHelper.CurlUp));
            case Transient.ID: return PMove.applyToRandom(99, PCLPowerHelper.Shackles);
            case WrithingMass.ID: return PMultiSkill.join(PMove.gainPlayer(1, PCLPowerHelper.Malleable), PMove.applyToRandom(1, PCLPowerHelper.Weak, PCLPowerHelper.Vulnerable));
        }
        return PMove.gainEnergy(1);
    }

    public int getValue() {
        return 2;
    }
}