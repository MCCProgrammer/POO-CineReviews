# 🎬 CineReviews — Object-Oriented Programming, Project II

<p>
  <img alt="Java" src="https://img.shields.io/badge/Java-1f2328?style=flat-square&logo=openjdk&logoColor=white">
  <img alt="Course" src="https://img.shields.io/badge/Course-Object--Oriented%20Programming%20(POO)-1f2328?style=flat-square">
  <img alt="Year" src="https://img.shields.io/badge/2022%2F23-1st%20year%2C%202nd%20semester-1f2328?style=flat-square">
</p>

Second project of *Programação Orientada pelos Objetos* (Object-Oriented Programming), Computer Science degree at **NOVA School of Science and Technology (FCT NOVA)**. It follows [Project I — Gossips](https://github.com/MCCProgrammer/POO-Gossips).

## About

A command-line **review platform for movies and series**, in the spirit of Rotten Tomatoes:

- **Admins** (with a password) upload movies and series — director or creator, duration or seasons, age rating, release year, genres and cast — and artists are created automatically.
- **Critics** and **audience** members review shows from *terrible* to *excellent*. Critics' reviews weigh **5× more** in a show's average score.
- Artists have a bio and a list of credits as actor, director or creator.
- Queries list shows by genre or release year, and find which artists have **worked together the most** — or **never** worked together.

## What I practiced

- **Class hierarchies** — abstract users, reviewers and shows with concrete admins, critics, audience members, movies and series
- **Custom checked exceptions** for every error case, instead of return codes
- The **Java Collections Framework** — maps, sorted sets and custom comparators for the rankings
- Keeping the user interface (`Main`) separate from the domain logic (`CineReviews`)

## Running it

Requires Java 8 or newer.

```bash
cd src
javac -d ../bin $(find . -name "*.java")
java -cp ../bin Main
```

Example session (type `help` to see every command):

```text
register admin miguel secret
User miguel was registered as admin.
register critic ana
User ana was registered as critic.
register audience rui
User rui was registered as audience.
movie miguel secret
Oppenheimer
Christopher Nolan
180
M/14
2023
2
drama
history
2
Cillian Murphy
Emily Blunt
Movie Oppenheimer (2023) was uploaded [3 new artists were created].
review ana Oppenheimer
A masterpiece about science and guilt.
excellent
Review for Oppenheimer was registered [1 reviews].
review rui Oppenheimer
Too long for me.
average
Review for Oppenheimer was registered [2 reviews].
reviews Oppenheimer
Reviews of Oppenheimer [4.7]:
Review of ana (critic): A masterpiece about science and guilt. [excellent]
Review of rui (audience): Too long for me. [average]
exit
Bye!
```

## Project structure

```
src/
├── Main.java               # command-line interface
├── Classes_Interfaces/     # users, shows, artists, reviews and the CineReviews system
└── Exceptions/             # one exception per error case
```

## Author

**Miguel Carmo** ([@MCCProgrammer](https://github.com/MCCProgrammer))

---

<sub>The assignment statement is not included in this repository, as it belongs to the course's teaching staff. This is a student project shared for portfolio purposes.</sub>
