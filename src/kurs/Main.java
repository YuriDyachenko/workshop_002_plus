package kurs;

//Для тех, кто хочет посложнее, анализатор текста должен вести себя несколько иначе. Он должен получать
//на вход три параметра: анализируемый текст, максимально допустимую длину, список запрещенных слов.
//Результатом работы этой функции должен быть JSON, в котором будут следующие поля:
//- "length" - длина строки
//- "pure_length" - длина строки без учета пробелов
//- "origin_text" - текст, полученный на входе
//- "pure_text" - текст, в котором все запрещенные слова из списка была заменены на три звездочки
//- "pure_short_text" - текст из pure_text, обрезанный на максимальном символе. Если этот символ не последний,
//надо это показать, добавив многоточие в конец.
//Дано:
//text: «Не забывайте о том, что все великие волшебники в истории в свое время были такими же, как мы, –
//школьниками. Если у них получилось, то получится и у нас», – Гарри Поттер.
//maxlen: 35
//forbidden_words: ["волшебники", "Гарри Поттер"]
//Результат функции:
//{
//"length":171,
//"pure_length":140
//"origin_text":"«Не забывайте о том, что все великие волшебники в истории в свое время были такими же, как мы, –
//школьниками. Если у них получилось, то получится и у нас», – Гарри Поттер."
//"pure_text":"«Не забывайте о том, что все великие *** в истории в свое время были такими же, как мы, –
//школьниками. Если у них получилось, то получится и у нас», – ***."
//"pure_short_text":"Не забывайте о том, что все великие..."
//}

public class Main {

    public static void main(String[] args) {
        String jSon = textAnalysisJSon("Не забывайте о том, что все великие волшебники в истории в свое " +
                "время были такими же, как мы, – школьниками. Если у них получилось, то получится и у нас»" +
                ", – Гарри Поттер",
                35, new String[] {"волшебники", "Гарри Поттер"});
        System.out.println(jSon);
    }

    private static String textAnalysisJSon(String text, int maxLength, String[] forbiddenWords) {
        String template =
                "{\n" +
                "    \"length\":%d,\n" +
                "    \"pure_length\":%d,\n" +
                "    \"origin_text\":\"%s\",\n" +
                "    \"pure_text\":\"%s\",\n" +
                "    \"pure_short_text\":\"%s\"\n" +
                "}\n";
        int textLength = text.length();

        String textWithoutSpaces = text.replace(" ", "");
        int textWithoutSpacesLength = textWithoutSpaces.length();

        String pureText = text;
        for (int i = 0; i < forbiddenWords.length; i++) {
            pureText = pureText.replace(forbiddenWords[i], "***");
        }

        String pureShortText = text.substring(0, maxLength) + (textLength > maxLength ? "..." : "");

        return String.format(template, textLength, textWithoutSpacesLength, text, pureText, pureShortText);
    }
}
