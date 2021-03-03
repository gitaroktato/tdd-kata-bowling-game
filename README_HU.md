## Story Outline
### Bowling Rules - Hungarian
https://www.funcitybowling.hu/bowling-szabalyok/

#### The Game
A játszma tíz körből áll. Minden körben kétszer guríthatsz, hogy leüss 10 bábut.
Az elért pontszámot a feldöntött bábuk száma adja.

#### Spare
SPARE: (/) Két gurításból döntöd le az összes bábut. Ehhez a mezőhöz a következő gurítás eredménye hozzáadódik.

#### Strikes
STRIKE: (X) Az első gurítással ledöntöd mind a 10 bábut.
Ilyenkor a második dobás elmarad, és a következő kettő gurítás eredménye adódik hozzá.

#### Tenth frame
A tizedik körben STRIKE vagy SPARE esetén extra gurítások lehetségesek.
Maximum 3 gurítás megengedett a tizedik körben. 

### Requirements
Write a class `Game` that has two methods

1. `void roll(int)` is called each time the player rolls a ball. The argument is the number of pins knocked down.
1. `int score()` returns the total score for that game.

Tags: #SOLID, #TDD, #Starter, #Outside-In, #Software-Design
