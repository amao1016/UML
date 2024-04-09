## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
## Problem resolved
- show name, change name
- select時的depth(反著遍歷)
- 創建obj不能在obj上點(解決depth的時候莫名其妙就跟著解決ㄌ)
- 移動物件(用lastObj)
- line連到port
## Problem now
- draw arrow箭頭尾巴還沒畫(現在畫的位置不對)
- Obj移動時已連的線跟著移動(目前想法：加入一個lineport紀錄已連線的port，或加入一個lineObj紀錄已被連線的obj) 紀錄線的應該要自己寫一個class，紀錄原obj
- group(可以group到有連線的物體ㄇ 阿好像應該要可以)
## murmur
為了解決select的選取還是拖曳，code變超醜= =
