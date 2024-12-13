# Polymer
It's a library for creating server side content, that work for player's without mods or (required) resource packs!
You can create blocks, items and entities, that not only will work fully server side (and singleplayer), but also
are still real custom ones, allowing for better integration with data packs and other mods (servers still see it as 
`mymod:custom` and not `minecraft:player_head`, unlike bukkit/spigot ones, that only emulate them).

This library also works correctly with other, non-polymer mods and [PolyMc](https://github.com/TheEpicBlock/PolyMc)!

## For players:
This library/mod includes few small utilities that can be useful for playing on servers using Polymer.

Few things it does on the client:

- Added compatibility for multiple client side mods ([EMI](https://modrinth.com/mod/emi),
  [Roughly Enough Items](https://modrinth.com/mod/roughly-enough-items), [WTHIT](https://modrinth.com/mod/wthit), [Jade](https://www.curseforge.com/minecraft/mc-mods/jade)
- Correct information on F3 debug screen
- Creative item tabs synced with server

## For server owners/mod pack makers:
Any mod using this library shouldn't be required on the client side! However, as stated above, there
are few small quality of life things added with it. So you might want to recommend it or include with
your mod pack.

If you have a server and polymer based mods you are using include a resource pack, you might want to install
"bundled/packed/all" version of polymer contains AutoHost module, allowing you to set up automatic building and sending of packs
to clients. See [this page](https://polymer.pb4.eu/latest/user/resource-pack-hosting) to learn about the setup.

## For mod developers:
All information about usage can be found at https://polymer.pb4.eu!

## What this library/mod doesn't do
This mod doesn't convert existing mods to server side ones. While it's possible to do so with manual coding,
for that you might want to use [PolyMc](https://github.com/TheEpicBlock/PolyMc) as long as you don't 
need to use regular mods on the client (for example with a modpack).

## Commands
- `/polymer` - Display about
- `/polymer creative` - Opens list of Polymer/Server-Side creative tabs (Available to anyone with creative)
- `/polymer generate-pack` - Generates polymer resourcepack as `<server/client directory>/polymer-resourcepack.zip`
- `/polymer stats` - Shows server side statistics
- `/polymer effects` - Shows server side status effects
- `/polymer client-item` - Displays client side nbt of held item
- `/polymer client-item get` - Gives player client side copy of held item
- `/polymer export-registry` - Exports registry information (debug command)

## Download
- [Modrinth](https://modrinth.com/mod/polymer)
- [Curseforge](https://www.curseforge.com/minecraft/mc-mods/polymer)
- [Github Releases](https://github.com/Patbox/polymer/releases)

### [Check list of known mods using Polymer](MODS.md)