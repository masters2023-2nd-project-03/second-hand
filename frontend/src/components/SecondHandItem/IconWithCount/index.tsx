import * as S from './styles';

import Icon from '../../Icon';
import iconfiles from '../../../assets/icons';

type IconName = keyof typeof iconfiles;

interface IconWithCountProps {
  name: IconName;
  count: number;
  checked: boolean;
}

const IconWithCount = ({ name, count, checked }: IconWithCountProps) => {
  const myCount = checked === true ? 'red' : '';

  return (
    <>
      {count > 0 && (
        <S.IconWithCountStyle>
          <Icon name={name} width={'20'} height={'16'} fill={myCount} />
          <span>{count}</span>
        </S.IconWithCountStyle>
      )}
    </>
  );
};

export default IconWithCount;
