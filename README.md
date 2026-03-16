# UDPCommunication

Progetto Java che implementa comunicazione UDP con **multicast**.

---

## Struttura del progetto

```
src/
├── Client/
│   └── MainClient.java
└── Server/
    └── MainServer.java
```

---

## Come funziona

### Multicast
Il server si unisce a un gruppo multicast (`230.0.0.1` porta `3000`) tramite `MulticastSocket`.  
Il client invia un pacchetto UDP all'indirizzo del gruppo — tutti i server uniti al gruppo lo ricevono.  
La risposta viene inviata in **unicast** direttamente al client mittente.

### Flusso di comunicazione

```
Client                          Server
  |                               |
  |--- UDP → 230.0.0.1:3000 ----> |  (multicast)
  |                               |
  |<--- UDP → client:porta -------|  (unicast)
```

---

## Configurazione

| Parametro        | Valore      |
|------------------|-------------|
| Indirizzo gruppo | `230.0.0.1` |
| Porta            | `3000`      |
| Buffer size      | `256` byte  |

> Gli indirizzi multicast validi sono nel range `224.0.0.0` – `239.255.255.255`.

---

## Avvio

1. Avviare prima **MainServer**
2. Avviare poi **MainClient**

Il server riceve il messaggio, lo stampa e risponde. Il client stampa la risposta con IP e porta del server.

---

## Requisiti

- Java 17+
- IntelliJ IDEA (o qualsiasi IDE con JDK configurato)
- Nessuna dipendenza esterna
