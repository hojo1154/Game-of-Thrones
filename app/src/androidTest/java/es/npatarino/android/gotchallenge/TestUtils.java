package es.npatarino.android.gotchallenge;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.domain.House;
import rx.Observable;

public class TestUtils {

    private static final GoTCharacter KHAL_DROGO = new GoTCharacter();
    private static final String KHAL_DROGO_NAME = "Khal Drogo";
    private static final String KHAL_DROGO_URL = "https://s3-eu-west-1.amazonaws.com/npatarino/got/8310ebeb-cdda-4095-bd5b-f59266d44677.jpg";
    private static final String KHAL_DROGO_DESCRIPTION = "Any description is good";
    private static final House KHAL_DROGO_HOUSE = new House();
    private static final String KHAL_DROGO_HOUSE_ID = "f96537a9";
    private static final String KHAL_DROGO_HOUSE_NAME = "House Khal Drogo";

    public static House defaultGotHouse() {
        House house = KHAL_DROGO_HOUSE;
        house.setHouseId(KHAL_DROGO_HOUSE_ID);
        house.setHouseName(KHAL_DROGO_HOUSE_NAME);
        house.setHouseImageUrl(KHAL_DROGO_URL);
        return house;
    }

    @NonNull
    public static List<GoTCharacter> getGoTCharacters(int numberOfGotCharacters) {
        List<GoTCharacter> characters = new LinkedList<>();
        for (int i = 0; i < numberOfGotCharacters; i++){
            characters.add(defaultGotCharacter());
        }
        return characters;
    }

    public static Observable<List<GoTCharacter>> getCharacters(int numberOfGotCharacters) throws Exception {
        List<GoTCharacter> characters = getGoTCharacters(numberOfGotCharacters);
        return Observable.just(characters);
    }

    public static Observable<List<GoTCharacter>>  getDelayedCharacters(int numberOfGotCharacters) throws Exception {
        return Observable.create(subscriber -> {
            List<GoTCharacter> characters = getGoTCharacters(numberOfGotCharacters);
            try {
                Thread.sleep(4000);

                subscriber.onNext(characters);
                subscriber.onCompleted();
            } catch (InterruptedException e) {
                e.printStackTrace();
                subscriber.onError(e);
            }
        });
    }


    public static GoTCharacter defaultGotCharacter(){
        GoTCharacter gotCharacter = KHAL_DROGO;
        gotCharacter.setName(KHAL_DROGO_NAME);
        gotCharacter.setImageUrl(KHAL_DROGO_URL);
        gotCharacter.setDescription(KHAL_DROGO_DESCRIPTION);
        gotCharacter.setHouseId(KHAL_DROGO_HOUSE_ID);
        gotCharacter.setHouseName(KHAL_DROGO_HOUSE_NAME);
        gotCharacter.setHouseImageUrl(KHAL_DROGO_URL);
        return gotCharacter;
    }


}
