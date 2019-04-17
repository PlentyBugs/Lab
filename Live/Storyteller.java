package Lab.Live;

import Lab.Exceptions.IsNakedException;
import Lab.Help.IntroductoryWords;
import Live.*;
import Lab.Locations.CarService;
import Lab.Locations.City;
import Lab.Locations.LocationAction;
import Lab.Locations.Road;
import Lab.Things.Car;
import Lab.Things.Details;
import Lab.Things.Thing;
import Lab.Things.ThingAction;
import Lab.Windows.Console;

public class Storyteller implements StorytellerAction {

    private String fileName = "";
    private Console console;
    private CarService carService;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public void tellStory() throws IsNakedException {

        // Подключаем вводные слова
        IntroductoryWords introductoryWords = new IntroductoryWords();
        introductoryWords.setConsole(console);

        // Локации
        City city = new City();
        city.setConsole(console);
        Road road = new Road();
        road.setConsole(console);
        City zmeevka = new City("город Змеевка");
        zmeevka.setConsole(console);

        // Вещи
        Car car = new Car();
        car.setConsole(console);
        car.addActions(
                new ThingAction.Stay("", "посреди дороги", Sex.MALE).setConsole(console),
                new ThingAction.Work(Sex.MALE).setConsole(console)
        );

        // Персонажи
        Cog cog = new Cog("Винтик", "механик", Sex.MALE){
            @Override
            public void addExp(int count){
                exp += Math.round(count*1.3);
                levelUp();
            }
        };
        cog.setConsole(console);
        Shpuntick shpuntick = new Shpuntick("Шпунтик", "", Sex.MALE){
            @Override
            public void addExp(int count){
                exp += Math.round(count*0.7);
                levelUp();
            }
        };
        shpuntick.setConsole(console);
        Driver driver = new Driver("Водитель", "водитель", Sex.MALE){
            @Override
            public void addExp(int count){
                exp += Math.round(count*1.7);
                levelUp();
            }
        };
        driver.setConsole(console);

        cog.addActions(
                new PersonAction.Step(cog.sex).setConsole(console),
                new PersonAction.See(car.getName(), cog.sex).setConsole(console),
                new PersonAction.RepairCar(Sex.MALE, car, cog).setConsole(console),
                new PersonAction.Found(cog.sex).setConsole(console),
                new PersonAction.Scratch(cog.sex).setConsole(console),
                new PersonAction.Tell(cog.sex).setConsole(console)
                );
        shpuntick.addActions(
                new PersonAction.Step(cog.sex).setConsole(console),
                new PersonAction.See(car.getName(), cog.sex).setConsole(console),
                new PersonAction.RepairCar(Sex.MALE, car, shpuntick).setConsole(console),
                new PersonAction.Scratch(shpuntick.sex).setConsole(console),
                new PersonAction.Tell(shpuntick.sex).setConsole(console)
                );
        driver.addActions(
                new PersonAction.RepairCar(driver.sex, car, driver).setConsole(console),
                new PersonAction.StuckOut(driver.sex).setConsole(console),
                new PersonAction.Kicked(driver.sex).setConsole(console),
                new PersonAction.Release(driver.sex).setConsole(console),
                new PersonAction.Search(driver.sex).setConsole(console),
                new PersonAction.Lay(driver.sex).setConsole(console),
                new PersonAction.Ride(driver.sex).setConsole(console),
                new PersonAction.GetAround(driver.sex).setConsole(console),
                new PersonAction.Inspect(driver.sex).setConsole(console),
                new PersonAction.Found(driver.sex).setConsole(console),
                new PersonAction.Scratch(driver.sex).setConsole(console),
                new PersonAction.Dive(driver.sex).setConsole(console),
                new PersonAction.PockedAround(driver.sex).setConsole(console),
                new PersonAction.PoppedUp(driver.sex).setConsole(console),
                new PersonAction.BeHappy(driver.sex).setConsole(console),
                new PersonAction.Shake(driver.sex).setConsole(console)

        );

        city.addPersons(cog, shpuntick);
        zmeevka.addAction(
                new LocationAction.gotJealous("вдали", "", Sex.MALE).setConsole(console)
        );

        city.moteToAnotherLocation(road, cog, shpuntick);

        introductoryWords.lessThanOneHour();
        zmeevka.useAction("Завиднеться");

        introductoryWords.thatTime();
        road.usePersonAction("Видеть", "", "", cog, shpuntick);
        road.addThings(car);
        car.useAction("Стоять");

        road.usePersonAction("Шагать", "", "", cog, shpuntick);
        road.usePersonAction("Видеть", "под машиной", driver.getRacePlayer(), cog, shpuntick);

        Thing head = new Thing("Голова");
        head.setConsole(console);
        Thing body = new Thing("Тело");
        body.setConsole(console);
        Thing legs = new Thing("Ноги");
        legs.setConsole(console);
        head.setProperty("черноволосая курчавая");

        road.addPersons(driver);

        body.addActions(
                new ThingAction.Hide("целиком","под кузовом",Sex.NEUTRAL).setConsole(console)
        );
        head.addActions(
                new ThingAction.Hide("целиком","под кузовом",Sex.FEMALE).setConsole(console)
        );
        legs.addActions(
                new ThingAction.StickOut("в черных засаленных брюках","наружу",Sex.MULTI).setConsole(console)
        );

        driver.addThings(body, head, legs);

        driver.useThingAction("Скрываться", "", "", body, head);
        driver.useThingAction("Торчать", "", "", legs);

        driver.useAction(driver.sex, head.getProperty() + " " + head.getName(),"из-под машины", "Высунуть");
        driver.useAction(driver.sex, "","наружу", "Выйти");
        driver.useAction(driver.sex, "в сердцах","колесо", "Пнуть");

        Thing jacket = new Thing("куртка");
        jacket.setConsole(console);
        Thing trousers = new Thing("брюки");
        trousers.setConsole(console);
        jacket.setProperty("засаленная");
        trousers.setProperty("засаленные");
        jacket.addActions(new ThingAction.Seem(Sex.FEMALE).setConsole(console));
        trousers.addActions(new ThingAction.Seem(Sex.MULTI).setConsole(console));

        driver.addClothes(jacket, trousers);
        driver.describeClothes();
        driver.useThingAction("Казаться", "будто сделаны из кожи", "", jacket, trousers);
        setLine();

        introductoryWords.asSee();
        driver.addLessNeededActions(new PersonAction.Ride("на ней", driver.sex).setConsole(console));
        driver.addMoreNeededActions(new PersonAction.Lay("под ней", driver.sex).setConsole(console), new PersonAction.Search("разного рода", "неисправности", driver.sex).setConsole(console));
        driver.useLessMoreNeededActions();

        setLine();

        driver.useAction(driver.sex,"вокруг",car.getName(), "Обойти");
        driver.useAction(driver.sex,"", "механизм", "Осмотреть");
        driver.useAction(driver.sex,"", "примину", true,  "Найти");
        driver.useAction(driver.sex,"под машину", "", "Нырнуть");
        driver.useAction(driver.sex,"под ней", "", "Поковыряться");
        driver.useAction(driver.sex,"обратно", "", "Вынырнуть");
        driver.useAction(driver.sex,"", "", "Стоять");
        driver.useAction(driver.sex,"в задумчивости", "затылок", "Чесать");

        setLine();

        for (Details obj : car.getDetails()){
            if (obj.getQuality() < 100){
                cog.useAction(cog.sex, "","затылок", "Чесать");
                cog.useAction("Чинить");
                obj.setQuality(obj.getQuality() + cog.getProfessionLvl() + cog.getLvl());
                shpuntick.useAction(shpuntick.sex, "","затылок", "Чесать");
                shpuntick.useAction("Чинить");
                obj.setQuality(obj.getQuality() + shpuntick.getProfessionLvl() + shpuntick.getLvl());
                driver.useAction(driver.sex, "","затылок", "Чесать");
                driver.useAction("Чинить");
                obj.setQuality(obj.getQuality() + driver.getProfessionLvl() + driver.getLvl());
                setLine();
            }
        }
        console.writeToConsole("Машина в идеальном состоянии");
        setLine();

        cog.useAction(cog.sex, "", "причину остановки мотора","Найти");

        car.useAction(Sex.MALE,"теперь","","Работать");

        setLine();

        driver.useAction("БытьРадостным");
        driver.useAction(driver.sex, "Винтику и Шпунтику","руки","Пожимать");

        setLine();

        cog.useAction(cog.sex, "о цели своего путешествия","ему","Рассказать");
        shpuntick.useAction(cog.sex, "о цели своего путешествия","ему","Рассказать");

        console.writeToConsole("------------------------------------");
        console.writeToConsole("Винтик и Шпунтик решили открыть свой автосалон, где они будут чинить машины, взяв в рабство водителя");
        console.writeToConsole("------------------------------------");

        for (String key : carService.getCars().keySet()){
            Car cr = carService.getCars().get(key);
            for(Details obj : cr.getDetails()){
                if (obj.getQuality() < 100){
                    cog.useAction(cog.sex, "","затылок", "Чесать");
                    cog.useAction("Чинить");
                    shpuntick.useAction(shpuntick.sex, "","затылок", "Чесать");
                    shpuntick.useAction("Чинить");
                    driver.useAction(driver.sex, "","затылок", "Чесать");
                    driver.useAction("Чинить");
                    setLine();
                }
            }
        }
    }

    public void setLine(){
        console.writeToConsole("------------------------------------");
    }

    public CarService getCarService() {
        return carService;
    }

    public void setConsole(Console console) {
        this.console = console;
    }
}
