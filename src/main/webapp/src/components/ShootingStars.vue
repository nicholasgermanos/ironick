<!--https://freefrontend.com/css-animation-examples/-->
<template lang="pug">
  .night
    - for (let i = 0; i < 2; i++) {
      .stars
        - for (let j = 0; j < 2; j++) {
          .stars-fast
            - for (let k = 0; k < 125; k++) {
              .star
            - }
          .stars-slow
            - for (let k = 0; k < 125; k++) {
              .star
            - }
        - }
        - for (let j = 0; j < 1; j++) {
          .shooting.blue
        - }
        - for (let j = 0; j < 1; j++) {
          .shooting.yellow
        - }
        - for (let j = 0; j < 1; j++) {
         .shooting.pink
        - }
    - }
</template>

<script>
export default {
  name: 'ShootingStars'
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
  background: linear-gradient(-45deg, #0a1433 0%, #222b3f 100%);
  border-radius: 100%;
  overflow: hidden;
}

.star {
  position: absolute;
  width: 1px;
  height: 1px;
}

.stars {
  @for $starBoxIndex from 1 through 2 {
    &:nth-child(#{$starBoxIndex}) {
      .stars-fast {
        .star {
          @for $starIndex from 1 through 125 {
            $light: random(255);
            $blink: random(30000) + 10000ms;
            &:nth-child(#{$starIndex}) {
              top: random(100) + 0%;
              left: random(100) + 0%;
              background: rgb($light, $light, $light);
              animation: star-blink $blink linear infinite;
            }
          }
        }

        position: absolute;
        width: 100%;
        height: 100%;
        animation: slide-down 50000ms linear infinite;
        top: -50%;
      }

      .stars-slow {
        .star {
          @for $starIndex from 1 through 125 {
            $light: random(255);
            $blink: random(30000) + 10000ms;
            &:nth-child(#{$starIndex}) {
              top: random(100) + 0%;
              left: random(100) + 0%;
              background: rgb($light, $light, $light);
              animation: star-blink $blink linear infinite;
            }
          }
        }

        position: absolute;
        width: 100%;
        height: 100%;
        animation: slide 30000ms linear infinite;
      }
    }
  }

  @for $starBoxIndex from 1 through 4 {
    .shooting {
      position: absolute;
      width: 45px;
      height: 1px;
      opacity: 0;
      animation: shooting random(3000) + 4000ms linear infinite;

      @for $shootingStarIndex from 1 through 10 {
        &:nth-child(#{$shootingStarIndex}) {
          top: random(80) + 0%;
          left: random(100) + 0%;
          animation-delay: random(9999) * 1ms;
        }
      }
    }
  }

  &:nth-child(1) {
    left: 100%;
  }

  position: absolute;
  width: 100%;
  height: 100%;
}

.yellow {
  background: linear-gradient(90deg, rgba(#fdf2b6, 1) 0%, rgba(#f6f3e6, 0) 100%);
}

.blue {
  background: linear-gradient(90deg, rgba(#9fcded, 1) 0%, rgba(#e0e8e6, 0) 100%);
}

.pink {
  background: linear-gradient(90deg, rgba(#f1c7f0, 1) 0%, rgba(#efe0ea, 0) 100%);
}

.white {
  background: linear-gradient(90deg, rgba(#f3f3f3, 1) 0%, rgba(#cacaca, 0) 100%);
}

@keyframes shooting {
  0% {
    opacity: 0;
    transform: rotateZ(-37deg) translateX(0) scaleX(0);
  }

  25% {
    opacity: 1;
    transform: rotateZ(-37deg) translateX(-200%) scaleX(1);
  }

  50% {
    opacity: 0;
    transform: rotateZ(-37deg) translateX(-400%) scaleX(2);
  }

  100% {
    opacity: 0;
    transform: rotateZ(-37deg) translateX(-400%) scaleX(2);
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

@keyframes slide-down {
  0% {
    transform: translateY(0) translateX(0);
  }

  25% {
    transform: translateY(10%) translateX(-50%);
  }

  100% {
    transform: translateY(-10%) translateX(-100%);
  }
}

@keyframes star-blink {
  0% {
    opacity: 1;
  }

  50% {
    opacity: 0;
  }

  100% {
    opacity: 1;
  }
}
</style>