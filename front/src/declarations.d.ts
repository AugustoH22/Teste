// src/declarations.d.ts

declare module '@heroui/chip' {
    import * as React from 'react';
  
    export interface ChipProps extends React.HTMLAttributes<HTMLDivElement> {
      children: React.ReactNode;
    }
  
    export const Chip: React.FC<ChipProps>;
  }
  