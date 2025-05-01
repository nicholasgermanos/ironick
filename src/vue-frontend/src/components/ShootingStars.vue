<!--https://freefrontend.com/css-animation-examples/-->
<template lang="pug">
.night
  - for (i = 0; i < 2; i++)
    .stars
      - for (j = 0; j < 16; j++)
        .shooting.yellow
      - for (j = 0; j < 16; j++)
        .shooting.blue
      - for (j = 0; j < 250; j++)
        .star
</template>

<script>
export default {
 name: "ShootingStars"
};
</script>

<style scoped lang="scss">
.night {
  position: fixed;
  width: 100%;
  height: 100%;
}

.window {
  position: absolute;
  width: 100%;
  height: 100%;
  background: linear-gradient(-45deg, #0a1433 0%,#222b3f 100%);
  border-radius: 100%;
  overflow: hidden;
}

.star {
  position: absolute;
  width: 1px;
  height: 1px;

  @for $i from 1 through 250 {
    $light: random(255);
    &:nth-child(#{$i}) {
      top: random(100) + 0%;
      left: random(100) + 0%;
      background: rgb($light, $light, $light);
    }
  }
}

.stars {
  position: absolute;
  width: 100%;
  height: 100%;
  animation: slide 30000ms linear infinite;

  &:nth-child(2) {
    left: 100%;
  }
}

.shooting {
  position: absolute;
  width: 50px;
  height: 1px;
  opacity: 0;
  animation: shooting 5000ms linear infinite;

  @for $i from 1 through 50 {
    &:nth-child(#{$i}) {
      top: random(100) + 0%;
      left: random(100) + 0%;
      animation-delay: random(9999) * 1ms;
    }
  }
}

.yellow {
  background: linear-gradient(90deg, rgba(#f5d719, 1) 0%, rgba(#f3ec80, 0) 100%);
}

.blue {
  background: linear-gradient(90deg, rgba(#0093E9, 1) 0%, rgba(#80D0C7, 0) 100%);
}

@keyframes shooting {
  0% {
    opacity: 0;
    transform: rotateZ(-30deg) translateX(0) scaleX(0);
  }

  25% {
    opacity: 1;
    transform: rotateZ(-30deg) translateX(-200%) scaleX(1);
  }

  50% {
    opacity: 0;
    transform: rotateZ(-30deg) translateX(-400%) scaleX(2);
  }

  100% {
    opacity: 0;
    transform: rotateZ(-30deg) translateX(-400%) scaleX(2);
  }
}


@keyframes slide {
  0% {
    transform: translateX(0);
  }

  100% {
    transform: translateX(-100%);
  }
}
</style>