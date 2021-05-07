Swift is a free and open-source injection hacked client base for Minecraft using Java-agents.

## Issues
If you notice any bugs, you can let us know by opening an issue [here](https://github.com/xWhitey/Swift/issues).

## License
This project is subject to the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html). This does only apply for source code located directly in this clean repository. During the development and compilation process, additional source code may be used to which we have obtained no rights. Such code is not covered by the GPL license.

For those who are unfamiliar with the license, here is a summary of its main points. This is by no means legal advice nor legally binding.

*Actions that you are allowed to do:*

- Use
- Share
- Modify

*If you do decide to use ANY code from the source:*

- **You must disclose the source code of your modified work and the source code you took from this project. This means you are not allowed to use code from this project (even partially) in a closed-source (or even obfuscated) application.**
- **Your modified application must also be licensed under the GPL**

## Setting up a Workspace
Swift uses MCP (9.40, 1.12.2 (modded MCP) ), to make sure that it is installed properly you can check other tutorials.
1. Clone the repository using `git clone --recurse-submodules https://github.com/xWhitey/Swift`. 
2. CD into the local repository.
3. Run `decompile`.
4. Unzip swift into `src (sources)\minecraft`.
5. Run `recompile`.
6. Run `reobfuscate`.
7. Open `reobf\minecraft`.
7. Create zip archive and drop team directory into archive.
8. Create META-INF directory in archive.
9. Open META-INF.
10. Create MANIFEST.MF file and open it.
11. Add these lines into MANIFEST.MF:
`Manifest-Version: 1.0`
`Agent-Class: team.swift.SwiftMain`
12. Save file and accept overwrite.
13. Rename created archive from `.zip` to `.jar`.
14. Use our [Loader](https://github.com/xWhitey/Loader) on this jar or create your own.

## ClickGUI
### AkrienClickGUI
Originally was made by Fals3R 3 years ago and got deobfuscated by xWhitey on 24.03.2021 due to client jar leak.
You can feel free to use it in your own project.

## Additional libraries
### Javassist
Javassist can be used to modify classes at runtime. Swift uses it to replace Minecraft code. If you want to learn more about it, check out its [Documentation](https://www.javassist.org/html/index.html).

## Contributing
We appreciate contributions. So if you want to support us, feel free to make changes to Swift's source code and submit a pull request.