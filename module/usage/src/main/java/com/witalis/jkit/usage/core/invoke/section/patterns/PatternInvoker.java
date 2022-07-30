package com.witalis.jkit.usage.core.invoke.section.patterns;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.chain.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.command.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.iterator.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.mediator.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.memento.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.observer.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.state.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.strategy.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.templatemethod.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.visitor.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.abstractfactory.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.builder.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.fabricmethod.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.prototype.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.simplefactory.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.singleton.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.adapter.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.bridge.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.composite.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.decorator.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.facade.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.flyweight.*;
import com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.proxy.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Desc: pattern expressions
 * User: Wellaxis
 * Date: 2021/07/26
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class PatternInvoker extends Invoker {

    public PatternInvoker() {
        setTitle("Design Patterns chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // creational
        log.info("## Creational patterns");
        invokeCreational();
        // tag
        log.info("");
        // structural
        log.info("## Structural patterns");
        invokeStructural();
        // tag
        log.info("");
        // behavioural
        log.info("## Behavioural patterns");
        invokeBehavioural();
        // tag
        log.info("");
        // concurrency
        log.info("## Concurrency patterns");
        invokeConcurrency();
    }

    /**
     * Basic postulates of design patterns.
     */
    private void invokeBasis() {
        // Design patterns are formalized best practices that the programmer can use
        // to solve common problems when designing an application or system.

        log.info(
            "Software design pattern is a reusable solution to a commonly occurring problem" +
            "within a given context in software design."
        );

        log.info(
            "Design patterns had originally been categorized into 3 sub-classifications: {}, {}, {}.",
            "creational", "structural", "behavioral"
        );

        // Creational patterns provide the capability to create objects
        // based on a required criterion and in a controlled way.

        // Structural patterns are about organizing different classes and objects
        // to form larger structures and provide new functionality.

        // Behavioral patterns are about identifying common communication patterns
        // between objects and realizing these patterns.
    }

    /**
     * Creational patterns.
     */
    private void invokeCreational() {
        // Creational patterns - provide the capability to create objects
        // based on a required criterion and in a controlled way.

        // simple factory
        {
            log.info("-- Simple Factory");
            Door door = DoorFactory.makeDoor(100, 200);
            log.info("Door width: {}", door.getWidth());
            log.info("Door height: {}", door.getHeight());
        }

        // fabric method
        {
            log.info("-- Fabric Method");
            HiringManager devMgr = new DevelopmentManager();
            devMgr.takeInterviewer();
            HiringManager tstMgr = new TestingManager();
            tstMgr.takeInterviewer();
        }

        // abstract factory
        {
            log.info("-- Abstract Factory");
            // factory
            TableFactory tableFactory = new WoodenTableFactory();
            // wooden
            Table woodenTable = tableFactory.makeTable();
            woodenTable.getDescription();
            TableExpert carpenter = tableFactory.makeExpert();
            carpenter.getDescription();
            // factory
            tableFactory = new IronTableFactory();
            // iron
            Table ironTable = tableFactory.makeTable();
            ironTable.getDescription();
            TableExpert welder = tableFactory.makeExpert();
            welder.getDescription();
        }

        // builder
        {
            log.info("-- Builder");
            Burger burger = Burger.newBuilder()
                .setSize(100)
                .setCheese(false)
                .setTomato(true)
                .build();
            log.info("Dinner: {}", burger.toString());
        }

        // prototype
        {
            log.info("-- Prototype");
            List<Shape> shapes = new ArrayList<>();
            // circle
            Circle circle = new Circle();
            circle.setX(10);
            circle.setY(20);
            circle.setRadius(5);
            circle.setColor("red");
            shapes.add(circle);
            // clone
            Circle newCircle = (Circle) circle.clone();
            newCircle.setColor("blue");
            shapes.add(newCircle);
            // rectangle
            Rectangle rectangle = new Rectangle();
            rectangle.setX(30);
            rectangle.setY(40);
            rectangle.setWidth(50);
            rectangle.setHeight(60);
            rectangle.setColor("green");
            shapes.add(rectangle);
            // clone
            Rectangle newRectangle = (Rectangle) rectangle.clone();
            newRectangle.setColor("yellow");
            shapes.add(newRectangle);
            // output
            log.info("Shapes: {}", shapes);
        }

        // singleton
        {
            log.info("-- Singleton");

            log.info("* Singleton Types");

            // eager initialization - created even though client application might not be using it
            {
                var eager = Singleton1EagerInitialized.getInstance();
                log.info("- Eager: {}", eager);
            }

            // static block initialization - the same, but provides option for exception handling
            {
                var block = Singleton2StaticBlock.getInstance();
                log.info("- Block: {}", block);
            }

            // lazy Initialization - works fine in case of the single-threaded environment only
            {
                var lazy = Singleton3LazyInitialized.getInstance();
                log.info("- Lazy: {}", lazy);
            }

            // thread Safe Singleton - reduces the performance because of the cost associated with the synchronized method
            {
                var safe = Singleton4ThreadSafe.getInstance();
                log.info("- Safe: {}", safe);
            }

            // double-checked locking - avoid extra overhead every time is used
            {
                var lock = Singleton5DoubleCheckedLocking.getInstance();
                log.info("- Locking: {}", lock);
            }

            // bill pugh singleton - it does not require synchronization, inner holder class
            {
                var pugh = Singleton6BillPugh.getInstance();
                log.info("- Inner: {}", pugh);
            }

            // enum singleton - protect from reflection, but does not allow lazy initialization
            {
                var enums = Singleton7Enum.getInstance();
                log.info("- Enum: {}", enums);
            }

            // serialized singleton - deserialization creates new instance, add readResolve() method
            {
                var serial = Singleton8Serialized.getInstance();
                log.info("- Serialized: {}", serial);
            }
        }
    }

    /**
     * Structural patterns.
     */
    private void invokeStructural() {
        // Structural patterns - are about organizing different classes and objects
        // to form larger structures and provide new functionality

        // adapter
        {
            log.info("-- Adapter");
            MovableAdapterImpl adapter = new MovableAdapterImpl();
            // car
            Movable car = new Car();
            log.info("Movable [car]: {} mp/h", car.getSpeed());
            adapter.setMovable(car);
            log.info("Movable [car]: {} km/h", adapter.getSpeed());
            // bicycle
            Movable bicycle = new Bicycle();
            log.info("Movable [bicycle]: {} mp/h", bicycle.getSpeed());
            adapter.setMovable(bicycle);
            log.info("Movable [bicycle]: {} km/h", adapter.getSpeed());
        }

        // bridge
        {
            log.info("-- Bridge");
            // theme
            Theme dark = new DarkTheme();
            // pages
            Page about = new AboutPage(dark);
            about.getContent();
            Page help = new HelpPage(dark);
            help.getContent();
            Page contact = new ContactPage(new LightTheme());
            contact.getContent();
        }

        // composite
        {
            log.info("-- Composite");
            // single
            Employee bob = new Employee("Bob", 35, true);
            Employee don = new Employee("Don", 15, true);
            Employee sam = new Employee("Sam", 73, true);
            Employee lex = new Employee("Lex", 28, true);
            // complex
            Team team = new Team(List.of(bob, don, lex));
            // manager
            TaskManager manager = new TaskManager();
            manager.setAssignees(List.of(bob, sam, team));
            manager.setTasks(List.of(new Work(), new Work(), new Work(), new Work(), new Work()));
            manager.performTasks();
        }

        // decorator
        {
            log.info("-- Decorator");
            // tea
            Drink tea = new Tea();
            log.info("Drink: {}, cost {}", tea.getDescription(), tea.getCost());
            Drink greenTea = new GreenTea(tea);
            log.info("Drink: {}, cost {}", greenTea.getDescription(), greenTea.getCost());
            // coffee
            Drink coffee = new Coffee();
            log.info("Drink: {}, cost {}", coffee.getDescription(), coffee.getCost());
            Drink milkCoffee = new MilkCoffee(coffee);
            log.info("Drink: {}, cost {}", milkCoffee.getDescription(), milkCoffee.getCost());
        }

        // facade
        {
            log.info("-- Facade");
            ComputerFacade computer = new ComputerFacade(new Computer());
            computer.turnOn();
            computer.turnOff();
        }

        // flyweight
        {
            log.info("-- Flyweight");
            // red
            Vehicle red = VehicleFactory.createVehicle("red");
            log.info("Red: {}", red);
            Vehicle newRed = VehicleFactory.createVehicle("red");
            log.info("Red: eq {}", red.equals(newRed));
            // blue
            Vehicle blue = VehicleFactory.createVehicle("blue");
            log.info("Blue: {}", blue);
            Vehicle newBlue = VehicleFactory.createVehicle("blue");
            log.info("Blue: eq {}", blue.equals(newBlue));
        }

        // proxy
        {
            log.info("-- Proxy");
            // room
            Room room = new LaboratoryRoom();
            room.open();
            room.close();
            // proxy
            Room proxy = new SecurityProxyRoom(room, "please");
            proxy.open();
            ((SecurityProxyRoom) proxy).setPassword("$ecr@t");
            proxy.open();
            proxy.close();
        }
    }

    /**
     * Behavioural patterns.
     */
    private void invokeBehavioural() {
        // Behavioural patterns - are about identifying common communication patterns
        // between objects and realize these patterns

        // chain of responsibility
        {
            log.info("-- Chain of responsibility");
            // account
            Account bank = new Bank("Bank", 100);
            Account paypal = new Paypal("Paypal", 200);
            Account bitcoin = new Bitcoin("Bitcoin", 300);
            // chain
            bank.setNext(paypal);
            paypal.setNext(bitcoin);
            // payment
            bank.pay(225);
            bank.pay(225);
        }

        // command
        {
            log.info("-- Command");
            // object
            Switchable bulb = new Bulb();
            // command
            Command turnOn = new TurnOn(bulb);
            Command turnOff = new TurnOff(bulb);
            // invoker
            RemoteControl invoker = new RemoteControl();
            invoker.submit(turnOn);
            invoker.submit(turnOff);
        }

        // iterator
        {
            log.info("-- Iterator");
            // repository
            NameRepository repository = new NameRepository();
            // iterator
            for (Iterator it = repository.getIterator(); it.hasNext();) {
                String name = it.next();
                log.info("Name: {}", name);
            }
        }

        // mediator
        {
            log.info("-- Mediator");
            // mediator
            ChatMediator mediator = new ChatRoom();
            // users
            ChatUser john = new ChatUser("John", mediator);
            ChatUser alex = new ChatUser("Alex", mediator);
            ChatUser duda = new ChatUser("Duda", mediator);
            // message
            john.send("Hello, all");
            duda.send("How are you?");
            alex.send("Better than you ;)");
        }

        // memento
        {
            log.info("-- Memento");
            // originator
            TextWindow textWindow = new TextWindow(this);
            // caretaker
            TextEditor textEditor = new TextEditor(textWindow);
            // action
            textEditor.write("The Memento Design Pattern; ");
            textEditor.write("Caretaker - Originator - Memento; ");
            textEditor.hitSave();
            textEditor.write("Try this pattern yourself...");
            textEditor.hitUndo();
            textEditor.write("Try this pattern yourself...");
            // memento
            log.info("Originator: {}", textEditor.getTextWindow().getCurrentText());
            log.info("Memento: {}", textEditor.getSavedTextWindow().getText());
        }

        // observer
        {
            log.info("-- Observer");
            // observable
            NewsAgency observable = new NewsAgency();
            // observers
            NewsChannel observer1 = new NewsChannel();
            NewsChannel observer2 = new NewsChannel();
            // initiate
            observable.addObserver(observer1);
            observable.addObserver(observer2);
            // state
            log.info("Observer[1]: {}", observer1.getNews());
            log.info("Observer[2]: {}", observer1.getNews());
            // notify
            observable.setNews("Hot news");
            // state
            log.info("Observer[1]: {}", observer1.getNews());
            log.info("Observer[2]: {}", observer1.getNews());
        }

        // visitor
        {
            log.info("-- Visitor");
            // objects
            Animal monkey = new Monkey();
            Animal lion = new Lion();
            Animal dolphin = new Dolphin();
            // visitor
            AnimalOperation speak = new Speak();
            monkey.accept(speak);
            lion.accept(speak);
            dolphin.accept(speak);
            // visitor
            AnimalOperation jump = new Jump();
            monkey.accept(jump);
            lion.accept(jump);
            dolphin.accept(jump);
        }

        // strategy
        {
            log.info("-- Strategy");
            // array
            int[] numbers = {3,7,2,9,3,8,4};
            log.info("less before: " + Arrays.toString(numbers));
            // invoker
            SortInvoker invoker = new SortInvoker(numbers);
            int[] sorted = invoker.sort();
            log.info("less items: " + Arrays.toString(sorted));
            // array
            numbers = new int[] {4,6,0,2,5,8,9,4,6,8,7,0,4,3};
            log.info("more before: " + Arrays.toString(numbers));
            // invoker
            invoker = new SortInvoker(numbers);
            sorted = invoker.sort();
            log.info("more items: " + Arrays.toString(sorted));
        }

        // state
        {
            log.info("-- State");
            // text
            String text = "Design Patterns";
            // editor
            WritingEditor editor = new WritingEditor(new Default());
            editor.type(text);
            editor.setState(new UpperCase());
            editor.type(text);
            editor.setState(new LowerCase());
            editor.type(text);
        }

        // template method
        {
            log.info("-- Template Method");
            // template n1
            Patchable android = new AndroidPatch();
            android.patch();
            // template n2
            Patchable ios = new AndroidPatch();
            ios.patch();
            // template n3
            Patchable windows = new Patch() {
                @Override
                public void test() {
                    log.info("\tWindows - test");
                }
                @Override
                public void build() {
                    log.info("\tWindows - build");
                }
                @Override
                public void deploy() {
                    log.info("\tWindows - deploy");
                }
            };
            windows.patch();
        }
    }

    /**
     * Concurrency patterns.
     */
    private void invokeConcurrency() {
        // Concurrency patterns - design patterns that deal
        // with the multithreaded programming paradigm

        log.info("-- Active Object");
        log.info("-- Balking");
        log.info("-- Binding properties");
        log.info("-- Compute kernel");
        log.info("-- Double-checked locking");
        log.info("-- Event-based asynchronous");
        log.info("-- Guarded suspension");
        log.info("-- Join");
        log.info("-- Lock");
        log.info("-- Messaging design pattern (MDP)");
        log.info("-- Monitor object");
        log.info("-- Reactor");
        log.info("-- Read-write lock");
        log.info("-- Scheduler");
        log.info("-- Thread pool");
        log.info("-- Thread-specific storage");
    }
}
