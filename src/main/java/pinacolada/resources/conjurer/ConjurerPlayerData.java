package pinacolada.resources.conjurer;

import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.exordium.*;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.conjurer.colorless.Iridescence;
import pinacolada.cards.pcl.status.Status_Wound;
import pinacolada.characters.ConjurerCharacter;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.effects.PCLEffect;
import pinacolada.effects.vfx.ConjurerScreenAnimationEffect;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.common.SorceryPower;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.powers.replacement.PCLLockOnPower;
import pinacolada.resources.PCLPlayerData;
import pinacolada.resources.conjurer.loadout.DarkSouls;
import pinacolada.resources.conjurer.loadout.EldenRing;
import pinacolada.resources.loadout.PCLLoadout;
import pinacolada.skills.PDelay;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PMultiSkill;

public class ConjurerPlayerData extends PCLPlayerData<ConjurerResources, ConjurerConfig, ConjurerCharacter> {
    public static ConjurerLoadout core = PCLLoadout.register(new ConjurerLoadout());

    public static ConjurerLoadout darkSouls = PCLLoadout.register(new DarkSouls());
    public static ConjurerLoadout eldenRing = PCLLoadout.register(new EldenRing());
    
    public ConjurerPlayerData(ConjurerResources resources) {
        super(resources, 70, 99, 5, 3, 0, 50, 20);
    }

    public static int getCostForMonster(String id) {
        if (id == null) {
            return 1;
        }
        switch (id) {
            case Champ.ID:
            case GiantHead.ID:
            case GremlinNob.ID:
            case Lagavulin.ID:
            case ShelledParasite.ID:
            case SlimeBoss.ID:
            case TheCollector.ID:
                return 3;
            case AwakenedOne.ID:
            case BanditPointy.ID:
            case BronzeAutomaton.ID:
            case Centurion.ID:
            case Cultist.ID:
            case Donu.ID:
            case GremlinLeader.ID:
            case Healer.ID:
            case Nemesis.ID:
            case Reptomancer.ID:
            case SnakePlant.ID:
            case TheGuardian.ID:
            case TimeEater.ID:
            case WrithingMass.ID:
                return 2;
        }
        return 1;
    }

    public static PSkill<?> getSkillForMonster(String id) {
        if (id == null) {
            return null;
        }
        switch (id) {
            case AcidSlime_L.ID:
            case AcidSlime_M.ID:
            case AcidSlime_S.ID:
                return PMove.applyToEnemies(1, PCLPowerData.Weak);
            case AwakenedOne.ID:
            case Cultist.ID:
                return PMove.gainPlayer(1, PCLPowerData.Ritual);
            case BanditBear.ID:
                return PMove.applyToEnemies(3, PCLPowerData.Bruised);
            case BanditLeader.ID:
                return PMove.applyToRandom(3, PCLPowerData.Weak);
            case BanditPointy.ID:
            case GremlinLeader.ID:
                return PMove.dealDamageToRandom(10, PCLAttackVFX.SLASH_DIAGONAL.key);
            case BookOfStabbing.ID:
                return PMultiSkill.join(PMove.dealDamageToAll(12, PCLAttackVFX.SLASH_DIAGONAL.key), PMove.createDrawPile(1, Status_Wound.DATA.ID));
            case BronzeAutomaton.ID:
                return PDelay.turnStart(4).setChild(PMultiSkill.join(PMove.dealDamageToAll(40, PCLAttackVFX.LIGHTNING.key), PMove.applyToEveryone(2, PCLPowerData.Vulnerable)));
            case Byrd.ID:
                return PMove.gainTemporaryPlayer(2, PCLPowerData.Flight);
            case Centurion.ID:
                return PMove.gainBlock(PCLCardTarget.None, 10);
            case Champ.ID:
            case ShelledParasite.ID:
                return PMove.gainPlayer(4, PCLPowerData.PlatedArmor);
            case Chosen.ID:
            case GremlinFat.ID:
                return PMove.applyToRandom(4, PCLPowerData.Weak);
            case Deca.ID:
                return PMove.gainBlock(PCLCardTarget.Team, 4);
            case Donu.ID:
                return PMove.applyToTeam(1, PCLPowerData.Strength);
            case Exploder.ID:
                return PDelay.turnStart(2).setChild(PMove.applyToEnemies(14, BlastedPower.DATA));
            case FungiBeast.ID:
                return PMove.applyToEnemies(1, PCLPowerData.Vulnerable);
            case GiantHead.ID:
                return PMove.applyToRandom(1, PCLPowerData.Slow);
            case GremlinNob.ID:
                return PMove.gainPlayer(2, PCLPowerData.Strength);
            case GremlinThief.ID:
            case TorchHead.ID:
                return PMove.dealDamage(9, PCLAttackVFX.SLASH_HORIZONTAL.key);
            case GremlinTsundere.ID:
                return PDelay.turnStart(1).setChild(PMove.gainBlock(PCLCardTarget.Team, 7));
            case GremlinWarrior.ID:
                return PMove.gainPlayer(5, PCLPowerData.Vigor);
            case GremlinWizard.ID:
                return PDelay.turnStart(2).setChain(PMove.applyToTeam(2, SorceryPower.DATA));
            case Healer.ID:
                return PMove.gainPlayer(1, PCLPowerData.Regen);
            case Hexaghost.ID:
                return PMove.applyToEnemies(7, IgnisPower.DATA);
            case JawWorm.ID:
                return PMultiSkill.join(PMove.gainBlockPlayer(3), PMove.dealDamage(3, PCLAttackVFX.BLUNT_LIGHT.key));
            case Lagavulin.ID:
                return PMove.gainPlayer(2, PCLPowerData.Metallicize);
            case Looter.ID:
            case Mugger.ID:
                return PMove.gainGold(4);
            case LouseDefensive.ID:
            case LouseNormal.ID:
                return PMove.gainPlayer(6, PCLPowerData.CurlUp);
            case Nemesis.ID:
                return PMove.gainPlayer(1, PCLPowerData.Intangible);
            case OrbWalker.ID:
                return PMove.applyToRandom(2, PCLLockOnPower.DATA);
            case Repulsor.ID:
                return PMove.draw(1);
            case Reptomancer.ID:
                return PMove.applyToRandom(10, PCLPowerData.Poison);
            case Sentry.ID:
                return PMove.gainPlayer(1, PCLPowerData.Artifact);
            case SlaverBlue.ID:
                return PMove.applyToRandom(3, PCLPowerData.Shackles);
            case SlaverRed.ID:
                return PMove.applyToRandom(3, PCLPowerData.Vulnerable);
            case SlimeBoss.ID:
                return PMove.applyToRandom(38, PCLPowerData.DelayedDamage);
            case SnakeDagger.ID:
                return PMove.applyToEnemies(3, PCLPowerData.Poison);
            case SnakePlant.ID:
                return PMove.gainPlayer(3, PCLPowerData.Malleable);
            case Snecko.ID:
                return PMove.create(Iridescence.DATA.ID);
            case SphericGuardian.ID:
                return PMove.gainPlayer(1, PCLPowerData.Blur);
            case SpikeSlime_L.ID:
            case SpikeSlime_M.ID:
            case SpikeSlime_S.ID:
                return PMove.applyToEnemies(2, PCLPowerData.Blinded);
            case Spiker.ID:
                return PMove.gainPlayer(3, PCLPowerData.Thorns);
            case Taskmaster.ID:
                return PMultiSkill.join(PMove.dealDamage(9, PCLAttackVFX.SLASH_DIAGONAL.key), PMove.applyToRandom(1, PCLPowerData.Vulnerable));
            case TimeEater.ID:
                return PMove.applyToEveryone(1, PCLPowerData.Silenced);
            case TheCollector.ID:
                return PMove.applyToRandom(3, PCLPowerData.Vulnerable, PCLPowerData.Weak, PCLPowerData.Frail);
            case TheGuardian.ID:
                return PMultiSkill.join(PMove.gainPlayer(3, PCLPowerData.Thorns), PMove.gainPlayer(10, PCLPowerData.CurlUp));
            case Transient.ID:
                return PMove.applyToRandom(15, PCLPowerData.Shackles);
            case WrithingMass.ID:
                return PMultiSkill.join(PMove.gainPlayer(1, PCLPowerData.Malleable), PMove.applyToRandom(1, PCLPowerData.Weak, PCLPowerData.Vulnerable));
        }
        return PMove.gainEnergy(1);
    }

    @Override
    public boolean canUseCustom() {
        return config.allowCustom.get();
    }

    @Override
    public boolean customDisablesProgression() {
        return true;
    }

    @Override
    public PCLEffect getCharSelectScreenAnimation() {
        return new ConjurerScreenAnimationEffect();
    }

    @Override
    public Class<ConjurerCharacter> getCharacterClass() {
        return ConjurerCharacter.class;
    }

    @Override
    public ConjurerCharacter createCharacter() {
        return new ConjurerCharacter();
    }

    @Override
    public ConjurerConfig getConfig() {
        return new ConjurerConfig();
    }

    @Override
    public PCLLoadout getCoreLoadout() {
        return core;
    }
}
