# InfinityLib - Slimefun 6 (Drake Framework Port)
### *A shaded library for 1.21.11 Expansion Addons*

Este componente ha sido portado y optimizado como parte del ecosistema **Slimefun 6** dentro del **Drake Framework**.

---

## 🤝 Créditos y Autoría
- **Autor Original**: [Mooy1](https://github.com/Mooy1)
- **Port a 1.21.11 (fork Drake):** [DrakesCraft-Labs](https://github.com/SlimefunNewHorizons)

---

## Origen y estado

- Fuente extraída de `drakes-slimefun-labs` (`sources/batch-2-expansion/InfinityLib`), archivo de solo
  lectura. Este repo es ahora la fuente canónica del artefacto
  `com.github.drakescraft_labs:infinitylib-drake` (paquete `dev.drake.infinitylib`), que sombrean
  InfinityExpansion, Gastronomicon, FoxyMachines, CrystamaeHistoria, SimpleUtils y Supreme.
- No confundir con `InfinityLib-Drake` (paquete `io.github.mooy1.infinitylib`, otra variante).
- `main` — **1.21.11 / Java 21**: `1.3.11-DRAKE-SNAPSHOT` contra el core propietario
  `slimefun-core 11.0-Drake-1.21.11-SNAPSHOT` (el que usa producción).
- `port-26x` — **26.x / Java 25**: `1.3.11-UNIVERSAL-26x-SNAPSHOT` contra el core universal
  `11.0-Universal-26.x-SNAPSHOT` (`io.github.thebusybiscuit.slimefun4.*`). Versión distinta a propósito
  para no pisar el artefacto 1.21.11 en `~/.m2`.
- Tests: 6 clases heredadas de upstream están excluidas en `pom.xml` porque nunca llegaron a ejecutarse
  y fallan o se cuelgan con MockBukkit 4.x; el resto de la suite sí corre.

---

# InfinityLib
A shaded library for Slimefun addons which adds a bunch of useful classes and utilities.

[![Release](https://jitpack.io/v/Mooy1/InfinityLib.svg)](https://jitpack.io/#Mooy1/InfinityLib)

# Packages & Features
## Core
<b>AbstractAddon</b>: An implementation of JavaPlugin
which you will need to extend for many of the other features to work.
It provides multiple utility methods and does some basic setup for you.

<b>AddonConfig</b>: is an implementation of YamlConfiguration
which makes comments available in the user's config
and provides utility methods such as getting a value from within a range
and removing unused/old keys from the user's config.

## Common
<b>CoolDowns</b>: A utility object for keeping track of cool downs of players/uuids

<b>PersistentType</b>: Contains some PersistentDataTypes for
ItemStack's, ItemStack Array's, Locations, and String Arrays.
Also provides a constructor for PersistentDataType that uses lambda parameters.

<b>Events</b>: Contains static utility methods for registering listeners, creating handlers, and calling events

<b>Scheduler</b>: Contains static utility methods for running and repeating tasks

## Commands
<b>AddonCommand</b>: allows you to add commands easily with a parent-child structure,
so you could have a command with a sub command which has a sub command.
It also adds some default commands such as an addon info, aliases, and help command.

## Groups
<b>MultiGroup</b>: An implementation of ItemGroup which lets you organize your groups into SubGroups

<b>SubGroup</b>: An ItemGroup that is hidden from the main page, for use in MultiGroup

## Machines
<b>MenuBlock</b>: A SlimefunItem with a menu, providing overridable methods for setting up the menu

<b>TickingMenuBlock</b>: A MenuBlock with slimefun ticker

<b>AbstractMachineBlock</b>: A TickingMenuBlock which implements EnergyNetComponent and provides a process method

<b>MachineBlock</b>: An AbstractMachineBlock which makes it easy to create simple input-output machines

## Future Additions
<b>Translation Utility</b>: Some sort of easy way to create translatable strings for your addon's and infinitylibs's strings

<b>InfinityLib Metrics</b>: Metrics to see which versions or even classes are being used

# How to use

First you need to add InfinityLib to the `dependencies` section in your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.mooy1</groupId>
    <artifactId>InfinityLib</artifactId>
    <version>SPECIFY VERSION HERE</version>
    <scope>compile</scope>
</dependency>
```

Then you need to relocate it into your own package so that it doesn't conflict with other addon's classes.

Under the `build` section in your `pom.xml`, you should have the following:

```xml
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.2.4</version>
        <configuration>
            <!-- This will exclude any unused classes from libraries to reduce file size, not required -->
            <minimizeJar>true</minimizeJar>
            <relocations>
                <!-- This is the relocation, make sure to replace the package name, REQUIRED -->
                <relocation>
                    <pattern>io.github.mooy1.infinitylib</pattern>
                    <shadedPattern>YOUR.MAIN.PACKAGE.HERE.infinitylib</shadedPattern>
                </relocation>
            </relocations>
            <filters>
                <filter>
                    <artifact>*:*</artifact>
                    <excludes>
                        <exclude>META-INF/*</exclude>
                    </excludes>
                </filter>
            </filters>
        </configuration>
        <executions>
            <execution>
                <phase>package</phase>
                <goals>
                    <goal>shade</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
</plugins>
```

Then change your main plugin class to extend `AbstractAddon` and implement the constructor.
You will need to use `enable()` and `disable()` instead of `onEnable()` and `onDisable`.
Make sure you don't call `super.onEnable/Disable`.
Your updater and config setup is now handled, make sure to test that it's working though!

<!-- DRAKES-STATUS:BEGIN -->
> Estado de sincronizacion: **2026-04-24**.
> Baseline tecnico vigente: **Paper 1.21.1 + Java 21**.
> CI principal en `main`: **Gates 1-5 en verde**.
> Nota: el monorepo completo sigue en migracion incremental por lotes.
<!-- DRAKES-STATUS:END -->
