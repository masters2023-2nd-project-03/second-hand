import * as S from './styles';

import Icon from '../Icon';
import currentTime from '../../utils/currentTime';

interface StatusBarProps {
  color: string;
}

const StatusBar = ({ color }: StatusBarProps) => {
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
