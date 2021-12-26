import java.io.BufferedReader
import java.io.File

fun main() {
//    Создание словаря для ролей
    val rolesDict = mutableMapOf<String, HashMap<Int, String>>()
//    Создание поток для чтения файла text.txt с исходными данными
    val streamReader = BufferedReader({}.javaClass.getResource("inputText.txt").openStream().buffered().reader())
//    Boolean переменная для определения ролей
    var readingRoles = false
//    счетчик для каждой из строк
    var counter = 1
//    forEach для чтения файла построчно
    streamReader.forEachLine { line ->
//        Проверка если мы еще считываем Роли
        if (line.contains("roles")) {
            readingRoles = true
//        Проверка если мы уже считываем реплики
        } else if (line.contains("textLines")) {
            readingRoles = false
//        Если мы закончили с чтением  текста, то переходим к распределению ролей
        } else {
            if (readingRoles) {
                rolesDict[line] = HashMap()
            } else {
//              Берем первую подстроку до : как роль
                val role = line.split(':')[0]
//              Берем вторую подстроку как фразу
                val rolePhrase = line.substring(line.indexOf(':') + 1).trim()
                val rolesPhrases = rolesDict[role]
                rolesPhrases?.let { it -> it[counter] = rolePhrase }
                counter++
            }
        }
    }
    File("./out/outputText.txt").bufferedWriter().use { buffWriter ->
        for (role in rolesDict.keys) {
            buffWriter.write("${role}:\n")
            val rolePhrases = rolesDict[role]
            rolePhrases?.let { rolePhrases ->
                for (phraseKey in rolePhrases.keys) {
                    buffWriter.write("${phraseKey} фраза: ${rolePhrases[phraseKey]}\n")
                }
            }
            buffWriter.newLine()
        }
    }
}
