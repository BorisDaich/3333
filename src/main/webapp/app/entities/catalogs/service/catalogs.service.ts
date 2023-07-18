import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICatalogs, NewCatalogs } from '../catalogs.model';

export type PartialUpdateCatalogs = Partial<ICatalogs> & Pick<ICatalogs, 'id'>;

type RestOf<T extends ICatalogs | NewCatalogs> = Omit<T, 'createdTime'> & {
  createdTime?: string | null;
};

export type RestCatalogs = RestOf<ICatalogs>;

export type NewRestCatalogs = RestOf<NewCatalogs>;

export type PartialUpdateRestCatalogs = RestOf<PartialUpdateCatalogs>;

export type EntityResponseType = HttpResponse<ICatalogs>;
export type EntityArrayResponseType = HttpResponse<ICatalogs[]>;

@Injectable({ providedIn: 'root' })
export class CatalogsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/catalogs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(catalogs: NewCatalogs): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(catalogs);
    return this.http
      .post<RestCatalogs>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(catalogs: ICatalogs): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(catalogs);
    return this.http
      .put<RestCatalogs>(`${this.resourceUrl}/${this.getCatalogsIdentifier(catalogs)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(catalogs: PartialUpdateCatalogs): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(catalogs);
    return this.http
      .patch<RestCatalogs>(`${this.resourceUrl}/${this.getCatalogsIdentifier(catalogs)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestCatalogs>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCatalogs[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCatalogsIdentifier(catalogs: Pick<ICatalogs, 'id'>): string {
    return catalogs.id;
  }

  compareCatalogs(o1: Pick<ICatalogs, 'id'> | null, o2: Pick<ICatalogs, 'id'> | null): boolean {
    return o1 && o2 ? this.getCatalogsIdentifier(o1) === this.getCatalogsIdentifier(o2) : o1 === o2;
  }

  addCatalogsToCollectionIfMissing<Type extends Pick<ICatalogs, 'id'>>(
    catalogsCollection: Type[],
    ...catalogsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const catalogs: Type[] = catalogsToCheck.filter(isPresent);
    if (catalogs.length > 0) {
      const catalogsCollectionIdentifiers = catalogsCollection.map(catalogsItem => this.getCatalogsIdentifier(catalogsItem)!);
      const catalogsToAdd = catalogs.filter(catalogsItem => {
        const catalogsIdentifier = this.getCatalogsIdentifier(catalogsItem);
        if (catalogsCollectionIdentifiers.includes(catalogsIdentifier)) {
          return false;
        }
        catalogsCollectionIdentifiers.push(catalogsIdentifier);
        return true;
      });
      return [...catalogsToAdd, ...catalogsCollection];
    }
    return catalogsCollection;
  }

  protected convertDateFromClient<T extends ICatalogs | NewCatalogs | PartialUpdateCatalogs>(catalogs: T): RestOf<T> {
    return {
      ...catalogs,
      createdTime: catalogs.createdTime?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restCatalogs: RestCatalogs): ICatalogs {
    return {
      ...restCatalogs,
      createdTime: restCatalogs.createdTime ? dayjs(restCatalogs.createdTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCatalogs>): HttpResponse<ICatalogs> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCatalogs[]>): HttpResponse<ICatalogs[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
