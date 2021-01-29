# Védd az invariánsaidat! - Egy gyakorlatias TDD bemutató

Hogyan kezeled az alkalmazásodon belül a korlátozásokat és a validációt?
A legtöbb fejlesztő szeret "modell-view-controller"-ekben gondolkodni,
és ezt a logikát valahova az alkalmazás határához, belépési pontjához helyezi el.
Valójában ez megakadályozza őket abban, hogy gazdag domain modellt akakítsanak ki,
és biztosítsák a megfelelő konzisztenciát.

Ebben a TDD köré épülő bemutatóban egy gyakorlati feladaton megyünk keresztül,
és azt lépésről lépésre oldjuk meg. A feladatot kisebb rész-problémákra osztjuk,
majd egyenként megoldva őket, egy működő modellt rakunk össze objektum kompozíció segítségével.

Megmutatom, miért hatékony a korlátozások és a validációk a domain belsejében való elhelyezése.
Megnézzük, hogyan lehet ezeket az "invariáns tulajdonságokat" objektum kompozíciókban hasznosítani.
