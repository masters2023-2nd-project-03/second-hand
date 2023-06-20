const elapsedTime = (createdAt: string) => {
  const minute = 60 * 1000;
  const hour = 60 * minute;
  const day = 24 * hour;
  const week = 7 * day;
  const month = 4 * week;
  const year = 12 * month;

  const currentDate = new Date();
  const createdDate = new Date(createdAt);
  const elapsedTime = currentDate.getTime() - createdDate.getTime();

  if (elapsedTime < hour) {
    return getMinutesAgo(elapsedTime);
  }

  if (elapsedTime < day) {
    return getHoursAgo(elapsedTime);
  }

  if (elapsedTime < week) {
    return getDaysAgo(elapsedTime);
  }

  if (elapsedTime < month) {
    return getWeeksAgo(elapsedTime);
  }

  if (elapsedTime < year) {
    return getMonthsAgo(elapsedTime);
  }

  return getYearsAgo(elapsedTime);
};

const getMinutesAgo = (elapsedTime: number) => {
  const minutes = Math.floor(elapsedTime / (60 * 1000));
  return minutes + '분 전';
};

const getHoursAgo = (elapsedTime: number) => {
  const hours = Math.floor(elapsedTime / (60 * 60 * 1000));
  return hours + '시간 전';
};

const getDaysAgo = (elapsedTime: number) => {
  const days = Math.floor(elapsedTime / (24 * 60 * 60 * 1000));
  return days + '일 전';
};

const getWeeksAgo = (elapsedTime: number) => {
  const weeks = Math.floor(elapsedTime / (7 * 24 * 60 * 60 * 1000));
  return weeks + '주 전';
};

const getMonthsAgo = (elapsedTime: number) => {
  const months = Math.floor(elapsedTime / (4 * 7 * 24 * 60 * 60 * 1000));
  return months + '개월 전';
};

const getYearsAgo = (elapsedTime: number) => {
  const years = Math.floor(elapsedTime / (12 * 4 * 7 * 24 * 60 * 60 * 1000));
  const remainingMonths = Math.floor(
    (elapsedTime % (12 * 4 * 7 * 24 * 60 * 60 * 1000)) /
      (4 * 7 * 24 * 60 * 60 * 1000),
  );

  if (remainingMonths === 0) {
    return years + '년 전';
  } else {
    return years + '년 ' + remainingMonths + '개월 전';
  }
};

export default elapsedTime;
