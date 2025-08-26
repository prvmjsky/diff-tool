### Hexlet tests and linter status:
[![Actions Status](https://github.com/prvmjsky/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/prvmjsky/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/f6a826b426cbc57f7a83/maintainability)](https://codeclimate.com/github/prvmjsky/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f6a826b426cbc57f7a83/test_coverage)](https://codeclimate.com/github/prvmjsky/java-project-71/test_coverage)

# Вычислитель отличий

Инструмент для определения разницы между двумя структурами данных, реализованный с учётом современных архитектурных решений и практик автоматизированного тестирования

---

## Принцип работы
Программа запускается в командной строке и принимает два файла с данными (в форматах JSON или YAML). После обработки выводит различия в выбранном формате (plain text, stylish или JSON). В процессе работы осуществляется парсинг входных данных, построение внутреннего представления дифа и его отображение. Поддерживаются автоматические тесты с использованием JUnit, что обеспечивает надежность и возможность рефакторинга

## Основные возможности
- Поддержка форматов: JSON и YAML
- Генерация отчетов в форматах: plain text, stylish, JSON
- Модульная архитектура с использованием структур данных и алгоритмов для обработки различий
- Работа с параметрами командной строки для выбора формата вывода и путей к файлам
- Тестирование с применением TDD и автоматизированных тестов

---

## О проекте

Проект выполнен в рамках курса "Java-разработчик" от онлайн-школы Hexlet

### Цели проекта:
- Разработка модульной архитектуры для сравнения структур данных
- Работа с файлами и парсингом форматов JSON и YAML
- Реализация алгоритмов построения дифа между структурами
- Создание удобного интерфейса командной строки

---

## Демонстрация работы приложения:
[![asciicast](https://asciinema.org/a/X9bj7xYd0abWxck1WsdmAw6Vh.svg)](https://asciinema.org/a/X9bj7xYd0abWxck1WsdmAw6Vh)
[![asciicast](https://asciinema.org/a/aOdrXIecpurMB77g6OHB3GnRB.svg)](https://asciinema.org/a/aOdrXIecpurMB77g6OHB3GnRB)
[![asciicast](https://asciinema.org/a/g0LL5O8TqzzqN2sweDNRDVfgm.svg)](https://asciinema.org/a/g0LL5O8TqzzqN2sweDNRDVfgm)
[![asciicast](https://asciinema.org/a/aKQx6029Rth7UfOhCBw1I17mM.svg)](https://asciinema.org/a/aKQx6029Rth7UfOhCBw1I17mM)
[![asciicast](https://asciinema.org/a/udyMBn89CRZatvHYKgUZV4AWv.svg)](https://asciinema.org/a/udyMBn89CRZatvHYKgUZV4AWv)
