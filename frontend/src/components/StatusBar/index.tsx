import React from 'react';
import Icon from '../Icon/icon';

import * as S from './styles';
import currentTime from './currentTime';

interface StatusBarProps {
  color: string;
}

const StatusBar: React.FC<StatusBarProps> = ({ color }) => {
  const timeTextColor = color === 'black' ? 'black' : 'white';

  return (
    <S.StatusBar>
      <div style={{ color: timeTextColor }}>{currentTime}</div>
      <S.Icons>
        <Icon name={'cellular'} width={'19'} height={'12'} fill={color} />
        <Icon name={'wifi'} width={'17'} height={'12'} fill={color} />
        <Icon name={'battery'} width={'27'} height={'13'} fill={color} />
      </S.Icons>
    </S.StatusBar>
  );
};

export default StatusBar;
