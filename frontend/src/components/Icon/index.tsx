import React from 'react';
import iconfiles from '../../assets/icons/index';

type IconName = keyof typeof iconfiles;

interface IconProps {
  name: IconName;
  width?: string;
  height?: string;
  fill?: string;
  onClick?: () => void; // onClick 속성 추가
}

const Icon = ({
  name = 'arrowUp',
  width = '24',
  height = '24',
  fill = 'black',
  onClick, // onClick 속성 추가
}: IconProps) => {
  const IconComponent = iconfiles[name];

  return (
    <div
      style={{ width: `${width}px`, height: `${height}px` }}
      onClick={onClick}
    >
      <IconComponent width={width} height={height} fill={fill} />
    </div>
  );
};

export default Icon;
