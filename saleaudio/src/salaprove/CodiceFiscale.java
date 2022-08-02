///////////////////////////////////////////////////////////////////////////////////////////////////////
//                                Codice Fiscale                                                     //
///////////////////////////////////////////////////////////////////////////////////////////////////////
package salaprove;

public class CodiceFiscale
{

        /***************************************************************************************************
                Codice fiscale. E' composto da 16 caratteri alfanumerici. L'ultimo carattere è il codice di controllo che vogliamo verificare:

            1. si pone s=0
            2. si considerano i caratteri di posto dispari dal primo al
            quindicesimo e si convertono in numeri secondo questa tabella:

                0 -> 1
                1 -> 0
                2 -> 5
                3 -> 7
                4 -> 9
                5 -> 13
                6 -> 15
                7 -> 17
                8 -> 19
                9 -> 21
                A -> 1
                B -> 0
                C -> 5
                D -> 7
                E -> 9
                F -> 13
                G -> 15
                H -> 17
                I -> 19
                J -> 21
                K -> 2
                L -> 4
                M -> 18
                N -> 20
                O -> 11
                P -> 3
                Q -> 6
                R -> 8
                S -> 12
                T -> 14
                U -> 16
                V -> 10
                W -> 22
                X -> 25
                Y -> 24
                Z -> 23

            I numeri corrispondenti a ciascun carattere vanno sommati ad s.

            3. si considerano i caratteri di posto pari dal secondo al
            quattordicesimo e si convertono in numeri secondo questa
            tabella:

                0 -> 0
                1 -> 1
                2 -> 2
                ...
                9 -> 9
                A -> 0
                B -> 1
                C -> 2
                ...
                Z -> 25

            Notare che alle cifre corrisponde il valore stesso, mentre alle lettere
            A-Z corrispondono i numeri 0-25.
            I numeri corrispondenti a ciascun carattere vanno sommati ad s.

            4. si calcola il resto della divisione di s per 26:
            r=s%26 cioe' r=s-26*int(s/26); risulta un numero tra 0 e 25;
            5. si converte r in una lettera associando a 0 la A, a 1 la B, ...,
            a 25 la Z
            6. la lettera ottenuta al punto 5 deve corrispondere all'ultimo
            carattere del cod.fiscale (che e' sempre una lettera).

        Esempio: ABCDEF12B23P432P

            1. s=0
            2. s=1+5+9+0+0+7+9+5
            3. s=s+1+3+5+2+2+15+3=67
            4. r=67%26=15
            5. al 15 corrisponde la lettera "P"
            6. OK.
**************************************************************************************************************/
        /**
              Costruttore Principale
        */
        public CodiceFiscale(){}

        /**
             Controllo del Codice Fiscale
        */
        public static String ControllaCF(String cf) {
            int i, s, c;
            String cf2;
            int setdisp[] = {1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20,
                11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24, 23 };
            if( cf.length() == 0 ) return "";
            if( cf.length() != 16 )
                return "La lunghezza del codice fiscale non e' corretta:\n"
                + " il codice fiscale dovrebbe essere lungo\n"
                + "esattamente 16 caratteri.";
            cf2 = cf.toUpperCase();
            for( i=0; i<16; i++ ){
                c = cf2.charAt(i);
                if( ! ( c>='0' && c<='9' || c>='A' && c<='Z' ) )
                    return "Il codice fiscale contiene dei caratteri non validi:\n"
                    + "i soli caratteri validi sono le lettere e le cifre.";
            }
            s = 0;
            for( i=1; i<=13; i+=2 ){
                c = cf2.charAt(i);
                if( c>='0' && c<='9' )
                    s = s + c - '0';
                else
                    s = s + c - 'A';
            }
            for( i=0; i<=14; i+=2 ){
                c = cf2.charAt(i);
                if( c>='0' && c<='9' )     c = c - '0' + 'A';
                s = s + setdisp[c - 'A'];
            }
            if( s%26 + 'A' != cf2.charAt(15) )
                return "Il codice fiscale non e' corretto:\n"
                + "il codice di controllo non corrisponde.";
            return "";
    }
}