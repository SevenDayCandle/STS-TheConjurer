package pinacolada.resources.conjurer;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class ConjurerEnum {


    public static class Library {
        @SpireEnum
        public static CardLibrary.LibraryType THE_CONJURER;

        public Library() {
        }
    }

    public static class Cards {
        @SpireEnum
        public static AbstractCard.CardColor THE_CONJURER;

        public Cards() {
        }
    }

    public static class Characters {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_CONJURER;

        public Characters() {
        }
    }
}
