import { css } from 'styled-components';

const typo = {
  largetitle: css`
    font-size: 34px;
    line-height: 41px;
    font-weight: 400;
  `,
  title1: css`
    font-size: 28px;
    line-height: 34px;
    font-weight: 400;
  `,
  title2: css`
    font-size: 22px;
    line-height: 28px;
    font-weight: 400;
  `,
  title3: css`
    font-size: 20px;
    line-height: 25px;
    font-weight: 400;
  `,
  headline: css`
    font-size: 17px;
    line-height: 22px;
    font-weight: 600;
  `,
  body: css`
    font-size: 17px;
    line-height: 24px;
    font-weight: 400;
  `,
  callout: css`
    font-size: 16px;
    line-height: 21px;
    font-weight: 400;
  `,
  subhead: css`
    font-size: 15px;
    line-height: 20px;
    font-weight: 400;
  `,
  footnote: css`
    font-size: 13px;
    line-height: 18px;
    font-weight: 400;
  `,
  caption1: css`
    font-size: 12px;
    line-height: 16px;
    font-weight: 400;
  `,
  caption2: css`
    font-size: 11px;
    line-height: 13px;
    font-weight: 400;
  `,
};

const color = {
  neutralText: css`
    color: #3c3c43;
  `,
  neutralTextWeak: css`
    color: rgba(60, 60, 67, 0.6);
  `,
  neutralTextStrong: css`
    color: #000000;
  `,
  neutralBackground: css`
    background-color: #ffffff;
  `,
  neutralBackgroundWeak: css`
    background-color: #fafafa;
  `,
  neutralBackgroundBold: css`
    background-color: rgba(118, 118, 128, 0.12);
  `,
  neutralBackgroundBulr: css`
    background-color: rgba(249, 249, 249, 0.8);
  `,
  neutralBorder: css`
    border-color: rgba(179, 179, 179, 0.39);
  `,
  neutralBorderStrong: css`
    border-color: rgba(60, 60, 67, 0.36);
  `,
  neutralOveray: css`
    background-color: rgba(0, 0, 0, 0.2);
  `,
  accentText: css`
    color: #ffffff;
  `,
  accentTextWeak: css`
    color: #000000;
  `,
  accentBackgroundPrimary: css`
    background-color: #ff9500;
  `,
  accentBackgroundSecondary: css`
    background-color: #00c7be;
  `,
  systemDefault: css`
    color: #007aff;
  `,
  systemWarning: css`
    color: #ff3b30;
  `,
  systemBackground: css`
    background-color: #ffffff;
  `,
  systemBackgroundWeak: css`
    background-color: rgba(245, 245, 245, 0.7);
  `,
};

const theme = {
  typo,
  color,
};

export default theme;
