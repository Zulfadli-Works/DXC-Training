new TypeIt("#type1", {
    speed: 120,
    loop: true,
    waitUntilVisible: true,
  })
    
    .type("HTML", { delay: 400 })
    .pause(500)
    .delete(10)
    .type("CSS", { delay: 400 })
    .pause(500)
    .delete(10)
    .type("Javascript", { delay: 400 })
    .pause(500)
    .delete(10)
    .type("Bootstrap", { delay: 400 })
    .pause(500)
    .delete(10)
    .type("MYSQL", { delay: 400 })
    .pause(500)
    .delete(10)
    .go();

    $(".animated").addClass("delay-1s");